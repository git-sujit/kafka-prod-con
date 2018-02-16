package com.sks.learn.kafkaudemy.demo;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class MyConsumer extends Thread {
	private final KafkaConsumer<Integer, String> consumer;
	private final String topic;

	public MyConsumer(String topic) {
		Properties props = new Properties();
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				KafkaProperties.KAFKA_BROKER_URL + ":" + KafkaProperties.KAFKA_BROKER_PORT);
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.IntegerDeserializer");
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, KafkaProperties.KAFKA_CONSUMER_GROUP);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

		consumer = new KafkaConsumer<Integer, String>(props);
		this.topic = topic;
	}

	@Override
	public void run() {
		consumer.subscribe(Collections.singleton(this.topic));
		ConsumerRecords<Integer, String> records = consumer.poll(1000);
		for (ConsumerRecord<Integer, String> record : records) {
			System.out.println(
					"Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
		}
	}

}
