package com.pru.flink.consumer;

import java.io.IOException;
import java.util.Properties;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pru.config.PropertyLoader;
import com.pru.constant.IntegrationConstants;
import com.pru.model.esb.NewBusinessModel;
import com.pru.service.ESBService;
import com.pru.service.impl.ESBServiceImpl;

/**
 * Class to read records from Kafka consumer and put those records into a file.
 *
 */
public class FlinkJsonConsumer {
	static Properties flinkPropConfig = new Properties();

	public static void main(String[] args) throws IOException {
		flinkPropConfig = PropertyLoader.getFlinkPropConfig();
//		flinkPropConfig.load(FlinkJsonConsumer.class.getClassLoader().getResourceAsStream("config.properties"));
		try {
			System.out.println("in kafka reader main");
			FlinkJsonConsumer flinkJsonConsumer = new FlinkJsonConsumer();
			flinkJsonConsumer.kafkaReader();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void kafkaReader() throws Exception {
		System.out.println("insider reader");

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);

		FlinkKafkaConsumer010<String> flinkKafkaConsumer = getFlinkKafkaConsumer011();
		DataStream<String> messageStream = env.addSource(flinkKafkaConsumer);

		FlinkKafkaProducer011<String> myProducer = new FlinkKafkaProducer011<String>(
				flinkPropConfig.getProperty(IntegrationConstants.BOOTSTRAP_SERVER),
				flinkPropConfig.getProperty(IntegrationConstants.NBS_PROPSAL_TOPIC), new SimpleStringSchema());

		messageStream.flatMap(new FlatMapFunction<String, NewBusinessModel>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void flatMap(String value, Collector<NewBusinessModel> out) throws Exception {
				System.out.println("flink 1a value" + value);
				ESBService esbService = new ESBServiceImpl();
				out.collect(esbService.generateNBSModel(value));
				// System.out.println("flink 1a value" + esbService.generateNBSModel(value));
			}
		}).flatMap(new FlatMapFunction<NewBusinessModel, String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void flatMap(NewBusinessModel value, Collector<String> out) throws Exception {
				// System.out.println("flink 1b value" + value);
				ObjectMapper mapper = new ObjectMapper();
				out.collect(mapper.writeValueAsString(value));
				System.out.println(mapper.writeValueAsString(value));
				// System.out.println("flink 1b value" + mapper.writeValueAsString(value));
			}
		}).addSink(myProducer);

		env.execute();

	}

	private FlinkKafkaConsumer011<String> getFlinkKafkaConsumer011() {
		Properties prop = new Properties();
//		prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
//				flinkPropConfig.getProperty(IntegrationConstants.BOOTSTRAP_SERVER));
//		prop.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
//				flinkPropConfig.getProperty(IntegrationConstants.SECURITY_PROTOCOL_CONFIG));
//		prop.setProperty(SaslConfigs.SASL_MECHANISM, flinkPropConfig.getProperty(IntegrationConstants.SASL_MECHANISM));

		prop.setProperty(IntegrationConstants.BOOTSTRAP_SERVER,
				flinkPropConfig.getProperty(IntegrationConstants.BOOTSTRAP_SERVER));
		prop.setProperty(IntegrationConstants.ZOOKEEPER_CONNECT,
				flinkPropConfig.getProperty(IntegrationConstants.ZOOKEEPER_CONNECT));
		prop.setProperty(IntegrationConstants.GROUP_ID, flinkPropConfig.getProperty(IntegrationConstants.GROUP_ID));
		FlinkKafkaConsumer011<String> flinkKafkaConsumer = new FlinkKafkaConsumer011<>(
				flinkPropConfig.getProperty(IntegrationConstants.POLICY_PROPSAL_TOPIC), new SimpleStringSchema(), prop);

		return flinkKafkaConsumer;

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
