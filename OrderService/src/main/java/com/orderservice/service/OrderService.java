package com.orderservice.service;

import com.orderservice.model.OrderModel;
import com.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OrderService {

    OrderModel saveOrder(OrderModel orderModel);
    List<OrderModel> getAllOrders();
    OrderModel getOrderById(String orderNumber);
}
