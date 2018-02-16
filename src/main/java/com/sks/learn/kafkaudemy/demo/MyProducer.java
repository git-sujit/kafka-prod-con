package com.sks.learn.kafkaudemy.demo;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyProducer extends Thread {
	private final KafkaProducer<Integer, String> producer;
	private final String topic;
	private final Boolean isAsync;

	public MyProducer(String topic, Boolean isAsync) {
		Properties props = new Properties();

		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				KafkaProperties.KAFKA_BROKER_URL + ":" + KafkaProperties.KAFKA_BROKER_PORT);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.IntegerSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.CLIENT_ID_CONFIG, KafkaProperties.CLIENT_ID);
		producer = new KafkaProducer<Integer, String>(props);
		this.topic = topic;
		this.isAsync = isAsync;
	}

	@Override
	public void run() {
		int messageNo = 1;
		while (true) {
			String message = "Message_" + messageNo;
			ProducerRecord<Integer, String> record = new ProducerRecord<Integer, String>(this.topic, messageNo,
					message);
			if (isAsync) {
				this.producer.send(record, new MyCallBack(System.currentTimeMillis(), messageNo, message));
			} else {
				try {
					this.producer.send(record).get();
					System.out.println("Sent message: (" + messageNo + ", " + message + ")");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
			messageNo++;
		}
	}

}
