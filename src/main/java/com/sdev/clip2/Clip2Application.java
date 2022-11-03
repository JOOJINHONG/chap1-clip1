package com.sdev.clip2;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Clip2Application {

	public static void main(String[] args) {
		SpringApplication.run(Clip2Application.class, args);
	}

	@Bean
	public ApplicationRunner runner(){
		return args -> {

		};
	}

}
