package com.globomantics;

import com.globomantics.model.Album;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.DoubleSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class AlbumSender {
	private static final Logger log = LoggerFactory.getLogger(AlbumSender.class);
	private static final String TOPIC = "connect-distributed";

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092,http://localhost:9093,http://localhost:9094");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, DoubleSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
		props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
		Album album = Album.newBuilder()
				.setName("Use Your Illusion")
				.setYear(1991).build();
		KafkaProducer<Double, Album> producer = new KafkaProducer<>(props);
		double key = Math.floor(Math.random()*(50));
		ProducerRecord<Double, Album> producerRecord =
				new ProducerRecord<>(TOPIC, key, album);

		log.info("Sending message " + album + " to Kafka");

		producer.send(producerRecord, (metadata, e) -> {
			if (metadata != null) {
				System.out.println(producerRecord.key());
				System.out.println(producerRecord.value());
				System.out.println(metadata.toString());
			}
		});
		producer.flush();
		producer.close();

		log.info("Successfully produced messages to " + TOPIC + " topic");

	}
}
