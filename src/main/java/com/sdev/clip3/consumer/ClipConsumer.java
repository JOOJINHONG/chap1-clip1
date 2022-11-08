package com.sdev.clip3.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClipConsumer {

    @KafkaListener(id = "clip3-id", topics = "clip3")
    public void listenClip3(String message) {
        System.out.println(message);
    }

}
