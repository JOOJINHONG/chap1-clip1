package com.sdev.clip4.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClipConsumer {

    @KafkaListener(id = "clip4-listener", topics = "clip4-listener", concurrency = "2" , clientIdPrefix = "listener-id")     //concurrency : 쓰레드 갯수 지정 , clientIdPrefix : id 커스텀
    public void listen(String message) {
        System.out.println("Listener. message = " + message);
    }


}
