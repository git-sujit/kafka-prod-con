package com.sks.learn.kafkaudemy.demo;

public class KafkaProperties {
	private KafkaProperties() {
	}

	public static final String KAFKA_BROKER_URL = "127.0.0.1";
	public static final String KAFKA_BROKER_PORT = "9092";
	public static final int KAFKA_CONNECTION_TIMEOUT = 100000;
	public static final String TOPIC_FIRST = "sks_first_topic";
	public static final String TOPIC_SECOND = "sks_second_topic";
	public static final String CLIENT_ID = "SKS_LEARNING";
	// For Kafka producer
	public static final int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;
	public static final String ACKS = "acks";

	// For Kafka consumer
	public static final String KAFKA_CONSUMER_GROUP = "LEARNING_CON_GROUP";
}
