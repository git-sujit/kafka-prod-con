package com.sks.learn.kafkaudemy;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducerDemo {
	private static Producer<String, String> kafkaProducer;

	public static void main(String[] args) {
		Properties props = new Properties();
		// Refer: https://kafka.apache.org/documentation.html#producerconfigs
		// Kafka bootstrap server
		props.setProperty("bootstrap.servers", "127.0.0.1:9092");
		props.setProperty("key.serializer", StringSerializer.class.getName());
		props.setProperty("value.serializer", StringSerializer.class.getName());
		// Acknowledgement from Leader(0, 1, all)
		props.setProperty("acks", "1");
		props.setProperty("retries", "3");
		props.setProperty("batch.size", "11000");
		props.setProperty("client.id", "sks_local");
		// Work around for kafka.flush
		props.setProperty("linger.ms", "1");

		// Kafka Producer
		kafkaProducer = new KafkaProducer<String, String>(props);
		for (int key = 0; key < 10; key++) {
			ProducerRecord<String, String> produceRecord = new ProducerRecord<String, String>("sks_second_topic",
					Integer.toString(key), "message-" + key);
			kafkaProducer.send(produceRecord);
		}
		// producer.flush();
		kafkaProducer.close();
	}

}
