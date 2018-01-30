package com.ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by august on 7/11/17.
 */
@Component
public class Sender {

    private static Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String data){
        logger.info("sending data={} to topic={}", data, topic);

        kafkaTemplate.send(topic, data);
    }
}
