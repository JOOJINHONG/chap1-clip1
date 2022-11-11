package com.sdev.clip3;

import com.sdev.clip3.producer.ClipProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class Clip3Application {

	public static void main(String[] args) {
		SpringApplication.run(Clip3Application.class, args);
	}
	@Bean
	public ApplicationRunner runner(ClipProducer clipProducer){	//Message 발송
		return args -> {
			clipProducer.async("clip3", "Hello, Clip3-async");
			clipProducer.sync("clip3", "Hello, Clip3-sync");
			//Thread.sleep(1000L);	//consumer가 없을시 서버 종료 딜레이
			clipProducer.routingSend("clip3", "Hello, Clip3-routing");
			clipProducer.routingSendBytes("clip3-bytes", "Hello, Clip3-bytes".getBytes(StandardCharsets.UTF_8));
			clipProducer.replyingSend("clip3-request", "Ping Clip3");
		};
	}

	/*@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate){	//Message 발송
		return args -> {
			kafkaTemplate.send("clip3", "Hello, Clip3");
		};
	}*/
}
