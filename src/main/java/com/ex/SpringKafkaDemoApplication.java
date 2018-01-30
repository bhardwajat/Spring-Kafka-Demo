package com.ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class SpringKafkaDemoApplication {

    private static Logger logger = LoggerFactory.getLogger(SpringKafkaDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaDemoApplication.class, args);
	}

	@Autowired
	Sender sender;

	@Autowired
	CountDownLatch oneSecondLatch;

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {

			sender.send("chat", "message");
			oneSecondLatch.await();
            sender.send("chat", "another message");
            oneSecondLatch.await();
            sender.send("chat", "sending another message");
            oneSecondLatch.await();
            sender.send("chat", "one last message");
            oneSecondLatch.await();
			logger.info("Messages Received");
		};
	}
}
