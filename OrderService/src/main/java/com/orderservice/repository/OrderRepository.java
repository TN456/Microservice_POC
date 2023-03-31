package com.orderservice.repository;

import com.orderservice.model.OrderModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderModel,String> {
}
