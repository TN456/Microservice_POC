package com.orderservice.controller;

import com.orderservice.model.OrderModel;
import com.orderservice.repository.OrderRepository;
import com.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<OrderModel> saveOrder(@Valid @RequestBody OrderModel orderModel){
        return new ResponseEntity<OrderModel>(orderService.saveOrder(orderModel), HttpStatus.CREATED);
    }

    @GetMapping("/getAllOrders")
    public List<OrderModel> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/OrderById/{id}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("orderNumber") String orderNumber){
        return new ResponseEntity<OrderModel>(orderService.getOrderById(orderNumber),HttpStatus.OK);
    }
}
