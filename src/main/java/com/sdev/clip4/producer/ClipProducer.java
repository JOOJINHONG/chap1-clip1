package com.sdev.clip4.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;


@Service
public class ClipProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ClipProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;

    }

    public void async(String topic, String message){    //비동기 방식의 처리
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new KafkaSendCallback<String, String>(){
            @Override
            public void onSuccess(SendResult<String, String> result) {  //성공시
                System.out.println("Success to send message!.");
            }

            @Override
            public void onFailure(KafkaProducerException ex) {  //실패시
                ProducerRecord<Object, Object> record = ex.getFailedProducerRecord();
                System.out.println("Fail to send message. record=" + record);
            }

        });
    }


}
