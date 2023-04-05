package com.orderservice.controller;

import com.orderservice.kafka.OrderProducer;
import com.orderservice.model.OrderModel;
import com.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProducer orderProducer;

    @PostMapping("/createOrder/myntra")
    @PreAuthorize("hasAuthority('myntra user')")
    public ResponseEntity<OrderModel> saveOrderMyntra(@Valid @RequestBody OrderModel orderModel) {
        OrderModel savedOrder = orderService.saveOrderMyntra(orderModel);
        orderProducer.sendMessage(savedOrder);
        return new ResponseEntity<OrderModel>(savedOrder, HttpStatus.CREATED);
    }

    @PostMapping("/createOrder/flipkart")
    @PreAuthorize("hasAuthority('flipkart user')")
    public ResponseEntity<OrderModel> saveOrderFlipKart(@Valid @RequestBody OrderModel orderModel) {
        OrderModel savedOrder = orderService.saveOrderFlipkart(orderModel);
        orderProducer.sendMessage(savedOrder);
        return new ResponseEntity<OrderModel>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/getAllOrders")
    @PreAuthorize("hasAuthority('admin')")
    public List<OrderModel> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/OrderById/{orderNumber}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("orderNumber") String orderNumber) {
        return new ResponseEntity<OrderModel>(orderService.getOrderById(orderNumber), HttpStatus.OK);
    }
}
