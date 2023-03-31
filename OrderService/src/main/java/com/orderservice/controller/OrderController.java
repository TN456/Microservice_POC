package com.orderservice.controller;

import com.orderservice.model.OrderModel;
import com.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/create")
    public ResponseEntity<OrderModel> saveOrder(@RequestBody OrderModel orderModel){
        return new ResponseEntity<OrderModel>(orderRepository.save(orderModel), HttpStatus.CREATED);
    }
}
