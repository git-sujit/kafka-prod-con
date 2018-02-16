package com.sks.learn.kafkaudemy;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumerDemo {

	private static Consumer<String, String> kafkaConsumer;

	public static void main(String[] args) {
		Properties props = new Properties();
		// Refer: https://kafka.apache.org/documentation/#consumerconfigs
		// Kafka bootstrap server
		props.setProperty("bootstrap.servers", "127.0.0.1:9092");
		props.setProperty("key.deserializer", StringDeserializer.class.getName());
		props.setProperty("value.deserializer", StringDeserializer.class.getName());
		props.setProperty("group.id", "Test");
		props.setProperty("enable.auto.commit", "true");
		props.setProperty("auto.commit.interval.ms", "1000");
		props.setProperty("auto.offset.reset", "earliest");

		kafkaConsumer = new KafkaConsumer<String, String>(props);
		kafkaConsumer.subscribe(Arrays.asList("sks_second_topic"));
		while (true) {
			ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
			for (ConsumerRecord<String, String> record : consumerRecords) {
				System.out.println("Partition-" + record.partition() + ", Offset- " + record.offset() + "  > "
						+ record.key() + ":" + record.value());
			}
			kafkaConsumer.commitSync();
		}
	}
}
