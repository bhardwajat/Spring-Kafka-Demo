package com.ex;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by august on 7/11/17.
 */
@Component
public class Reciever {

    private static Logger logger = LoggerFactory.getLogger(Reciever.class);

    @Autowired
    private CountDownLatch oneSecondLatch;

    public CountDownLatch getOneSecondLatch(){
        return this.oneSecondLatch;
    }

    @KafkaListener(topics="chat")
    public void receive(ConsumerRecord<?, ?> consumerRecord){
        logger.info("received data={} for topic={}", consumerRecord.toString(), consumerRecord.topic());
        oneSecondLatch.countDown();
    }
}
