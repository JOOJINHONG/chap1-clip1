package com.sdev.chap1clip1;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(id = "sdev-id",topics = "quickstart-events")
    public void listen(String message){
        System.out.println("==============");
        System.out.println(message);
        System.out.println("==============");
    }
}
