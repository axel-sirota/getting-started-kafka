package com.globomantics;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class SimpleETL {

	public static void main(String[] args) {
		Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "weather.filter");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092,http://localhost:9093,http://localhost:9094");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, 
        		Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, 
        		Serdes.Integer().getClass());
		
		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, Integer> rawReadings = builder.stream("RawTempReadings");
		KStream<String, Integer> validatedReadings = rawReadings
				.filter((key, value) -> value > -50 && value < 130);
		validatedReadings.to("ValidatedTempReadings");
		
		Topology topo = builder.build();
		System.out.println(topo.describe());

		KafkaStreams streams = new KafkaStreams(topo, props);
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
		streams.cleanUp();
		streams.start();


		System.out.println("Starting simple ETL");

	}

}
