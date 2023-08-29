package com.globomantics;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Producer {
	private static final Logger log = LoggerFactory.getLogger(Producer.class);
	private static final Properties props = new Properties();

	public static void main(String[] args) throws InterruptedException {
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092,http://localhost:9093,http://localhost:9094");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		KafkaProducer<String, Integer> producer = new KafkaProducer<>(props);
		String[] sensors = new String[]{"sensor_1", "sensor_2", "sensor_3"};
		Runtime.getRuntime().addShutdownHook(new Thread(producer::close));
		while (true) {
			int idx = new Random().nextInt(sensors.length);
			String key = (sensors[idx]);
			int value = ThreadLocalRandom.current().nextInt(-20, 180 + 1);
			ProducerRecord<String, Integer> producerRecord =
					new ProducerRecord<>("RawTempReadings", key, value);

			producer.send(producerRecord);

			producer.flush();
			log.info("Successfully produced message from sensor " + key);
			Thread.sleep(200);
		}

	}
}
