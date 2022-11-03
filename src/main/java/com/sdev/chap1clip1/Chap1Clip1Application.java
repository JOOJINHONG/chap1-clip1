package com.sdev.chap1clip1;

import org.apache.kafka.common.internals.Topic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Chap1Clip1Application {

	public static void main(String[] args) {
		SpringApplication.run(Chap1Clip1Application.class, args);
	}

	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate){
		return args -> {
			kafkaTemplate.send("quickstart-events", "hello-world");
		};
	}

}
