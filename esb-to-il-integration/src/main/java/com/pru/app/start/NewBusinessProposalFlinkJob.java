package com.pru.app.start;

import java.util.Properties;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pru.config.PropertyLoader;
import com.pru.constant.IntegrationConstants;
import com.pru.service.ILService;
import com.pru.service.impl.ILServiceImpl;

public class NewBusinessProposalFlinkJob {
	private final static Logger logger = LoggerFactory.getLogger(NewBusinessProposalFlinkJob.class);
	static Properties flinkPropConfig;
	static String path;

	private static void loadPath(String[] args) {
		logger.info("loadPath() start");
		final ParameterTool params = ParameterTool.fromArgs(args);
		path = params.get(IntegrationConstants.RESOURCE_PATH);
		logger.debug("resources base path :: {}", path);
		logger.info("loadPath() end");
	}

	public static void main(String[] args) throws Exception {
		logger.info("NewBusinessProposalFlinkJob main() start");

		flinkPropConfig = PropertyLoader.getNewBizProposalFlinkPropConfig();
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		Properties prop = new Properties();
		/*
		 * prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
		 * flinkPropConfig.getProperty(IntegrationConstants.BOOTSTRAP_SERVER));
		 * prop.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
		 * flinkPropConfig.getProperty(IntegrationConstants.SECURITY_PROTOCOL_CONFIG));
		 * prop.setProperty(SaslConfigs.SASL_MECHANISM,
		 * flinkPropConfig.getProperty(IntegrationConstants.SASL_MECHANISM));
		 */

		prop.setProperty(IntegrationConstants.BOOTSTRAP_SERVER,
				flinkPropConfig.getProperty(IntegrationConstants.BOOTSTRAP_SERVER));
		prop.setProperty(IntegrationConstants.ZOOKEEPER_CONNECT,
				flinkPropConfig.getProperty(IntegrationConstants.ZOOKEEPER_CONNECT));
		prop.setProperty(IntegrationConstants.GROUP_ID, flinkPropConfig.getProperty(IntegrationConstants.GROUP_ID));
		logger.debug("kafka property set successful");
		FlinkKafkaConsumer011<String> flinkKafkaConsumer = new FlinkKafkaConsumer011<>(
				flinkPropConfig.getProperty(IntegrationConstants.NEW_BUSS_PROPOSAL_TOPIC_NAME),
				new SimpleStringSchema(), prop);
		DataStream<String> messageStream = env.addSource(flinkKafkaConsumer);
		logger.debug("messageStream coming from kafka consumer");
		messageStream.flatMap(new FlatMapFunction<String, String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void flatMap(String value, Collector<String> out) throws Exception {
				logger.debug("message from kafka :: {}", value);
				try {
					ILService iLService = new ILServiceImpl();
					out.collect(iLService.serviceRequest(value));
				} catch (Exception e) {
					logger.error("error in main :: {} ", e);
				}
			}
		}).addSink(new PrintSinkFunction<>());
		env.execute();
		logger.info("NewBusinessProposalFlinkJob main() end");
	}

	public static class SimpleStringSchema implements DeserializationSchema<String>, SerializationSchema<String> {
		private static final long serialVersionUID = 1L;

		public SimpleStringSchema() {
		}

		public String deserialize(byte[] message) {
			return new String(message);
		}

		public boolean isEndOfStream(String nextElement) {
			return false;
		}

		public byte[] serialize(String element) {
			return element.getBytes();
		}

		public TypeInformation<String> getProducedType() {
			return TypeExtractor.getForClass(String.class);
		}

	}
}
