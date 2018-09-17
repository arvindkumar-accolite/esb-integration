package com.pru.flink.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;

//import com.pru.constant.ConfigConstants;
import com.pru.flink.consumer.FlinkJsonConsumer.SimpleStringSchema;

public class testDevFlink {

	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);

		Properties prop = new Properties();
//		prop.setProperty(ConfigConstants.BOOTSTRAP_SERVER, " https://aafprholdkaf007:9093");
//		prop.setProperty(ConfigConstants.ZOOKEEPER_CONNECT, " https://aafprholdkaf007:2181");
//		prop.setProperty(ConfigConstants.GROUP_ID, "abcd");
		FlinkKafkaConsumer010<String> flinkKafkaConsumer = new FlinkKafkaConsumer010<>("testTopic",
				new SimpleStringSchema(), prop);
//aafprholdkaf007:9093
		FlinkKafkaProducer011<String> myProducer = new FlinkKafkaProducer011<String>("localhost:9092", // broker
																														// list
				"testTopic", // target topic
				new SimpleStringSchema());

		DataStream<String> messageStream = env.fromCollection(Arrays.asList("a", "b"));

		messageStream.addSink(myProducer);

//		messageStream.flatMap(new FlatMapFunction<String, NewBusinessModel>() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void flatMap(String value, Collector<NewBusinessModel> out) throws Exception {
//				NewBusinessPopulator nbspop = new NewBusinessPopulator();
//				out.collect(nbspop.buildNewBusiness(value));
//			}
//		}).flatMap(new FlatMapFunction<NewBusinessModel, String>() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void flatMap(NewBusinessModel value, Collector<String> out) throws Exception {
//				
//				ObjectMapper mapper = new ObjectMapper();
//				out.collect(mapper.writeValueAsString(value));
//				
//			}
//		}).addSink(myProducer);

		env.execute();
	}
}
