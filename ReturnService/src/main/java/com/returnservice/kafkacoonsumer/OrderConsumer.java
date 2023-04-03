package com.returnservice.kafkacoonsumer;



import com.returnservice.model.ReturnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
    @Service
    public class OrderConsumer {

        private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
        @KafkaListener(topics = "topic_first_json",groupId = "myGroup")
        public void consume(ReturnModel returnModel) {
            LOGGER.info(String.format("Message received : %s", returnModel.toString()));
        }
    }




