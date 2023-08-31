package com.globomantics;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.DoubleDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092,http://localhost:9093,http://localhost:9094");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, DoubleDeserializer.class.getName());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer");

		KafkaConsumer<String, Double> consumer = new KafkaConsumer<>(props);

		Thread haltedHook = new Thread(consumer::close);
		Runtime.getRuntime().addShutdownHook(haltedHook);

		consumer.subscribe(Collections.singletonList("myorders"));

		while (true) {
			ConsumerRecords<String, Double> records = consumer.poll(Duration.ofMillis(100));
			records.forEach(Consumer::processRecord);
		}
	}

	private static void processRecord(ConsumerRecord record) {
		log.info("Received message with key: " + record.key() + " and value " + record.value());
		log.info("It comes from partition: " + record.partition());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}