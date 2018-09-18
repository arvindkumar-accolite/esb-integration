package com.pru.flink.consumer;

import java.util.Properties;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;
import org.apache.flink.util.Collector;

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
	static ParameterTool flinkPropConfig;
	static String path;

	public static void main(String[] args) {
		loadPath(args);
		new PropertyLoader(path);
		flinkPropConfig = PropertyLoader.getFlinkPropConfig();
		try {
			System.out.println("in kafka reader main");
			FlinkJsonConsumer flinkJsonConsumer = new FlinkJsonConsumer();
			flinkJsonConsumer.kafkaReader();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadPath(String[] args) {
		final ParameterTool params = ParameterTool.fromArgs(args);
		path = params.get(IntegrationConstants.RESOURCE_PATH);
	}

	private void kafkaReader() throws Exception {
		System.out.println("insider reader");
		
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		
		FlinkKafkaConsumer010<String> flinkKafkaConsumer = getFlinkKafkaConsumer010();
		DataStream<String> messageStream = env.addSource(flinkKafkaConsumer);
		
		FlinkKafkaProducer011<String> myProducer = new FlinkKafkaProducer011<String>(
				flinkPropConfig.get(IntegrationConstants.BOOTSTRAP_SERVER),
				flinkPropConfig.get(IntegrationConstants.NBS_PROPSAL_TOPIC), new SimpleStringSchema());
		
		messageStream.flatMap(new FlatMapFunction<String, NewBusinessModel>() {
			private static final long serialVersionUID = 1L;
			@Override
			public void flatMap(String value, Collector<NewBusinessModel> out) throws Exception {
				ESBService esbService = new ESBServiceImpl();
				out.collect(esbService.generateNBSModel(value));
			}
		}).flatMap(new FlatMapFunction<NewBusinessModel, String>() {
			private static final long serialVersionUID = 1L;
			@Override
			public void flatMap(NewBusinessModel value, Collector<String> out) throws Exception {
				ObjectMapper mapper = new ObjectMapper();
				out.collect(mapper.writeValueAsString(value));
				System.out.println(mapper.writeValueAsString(value));
			}
		}).addSink(myProducer);

		env.execute();

	}

	private FlinkKafkaConsumer010<String> getFlinkKafkaConsumer010() {
		Properties prop = new Properties();
		prop.setProperty(IntegrationConstants.BOOTSTRAP_SERVER,
				flinkPropConfig.get(IntegrationConstants.BOOTSTRAP_SERVER));
		prop.setProperty(IntegrationConstants.ZOOKEEPER_CONNECT,
				flinkPropConfig.get(IntegrationConstants.ZOOKEEPER_CONNECT));
		prop.setProperty(IntegrationConstants.GROUP_ID, flinkPropConfig.get(IntegrationConstants.GROUP_ID));
		FlinkKafkaConsumer010<String> flinkKafkaConsumer = new FlinkKafkaConsumer010<>(
				flinkPropConfig.get(IntegrationConstants.POLICY_PROPSAL_TOPIC), new SimpleStringSchema(), prop);

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
