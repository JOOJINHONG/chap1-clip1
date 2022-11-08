package com.sdev.clip3.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class ClipProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RoutingKafkaTemplate routingKafkaTemplate;

    public ClipProducer(KafkaTemplate<String, String> kafkaTemplate,
                        RoutingKafkaTemplate routingKafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
        this.routingKafkaTemplate = routingKafkaTemplate;
    }

    public void async(String topic, String message){    //비동기 방식의 처리
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new KafkaSendCallback<>(){

            @Override
            public void onFailure(KafkaProducerException ex) {  //실패시
                ProducerRecord<Object, Object> record = ex.getFailedProducerRecord();
                System.out.println("Fail to send message. record=" + record);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {  //성공시
                System.out.println("Success to send message.");
            }
        });
    }

    public void sync(String topic, String message){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        try {
            System.out.println("Succes to send sync message.");
            future.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

    }

    public void routingSend(String topic, String message){
        routingKafkaTemplate.send(topic, message); //메시지 확인은 consumer 필요
    }
}
