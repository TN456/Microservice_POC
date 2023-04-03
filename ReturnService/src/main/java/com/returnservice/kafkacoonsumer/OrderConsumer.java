package com.returnservice.kafkacoonsumer;



import com.orderservice.model.OrderModel;
import com.returnservice.model.ReturnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
    @Service
    public class OrderConsumer {

        private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
        @KafkaListener(topics = "${spring.kafka.topic.name}"
                , groupId = "${spring.kafka.consumer.group-id}")
        public void consume(OrderModel orderModel) {
            LOGGER.info(String.format("Message received : %s", orderModel.toString()));
        }
    }




