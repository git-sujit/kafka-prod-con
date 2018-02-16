package com.sks.learn.kafkaudemy.demo;

public class MyProdConTest {

	public static void main(String[] args) {
		boolean isAsync = args.length == 0 || !args[0].trim().equalsIgnoreCase("sync");
		MyProducer producerThread = new MyProducer(KafkaProperties.TOPIC_FIRST, isAsync);
		producerThread.start();

		MyConsumer consumerThread = new MyConsumer(KafkaProperties.TOPIC_FIRST);
		consumerThread.start();

	}

}
