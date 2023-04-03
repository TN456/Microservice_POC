package com.orderservice.service.impl;

import com.orderservice.exception.ResourceNotFoundException;
import com.orderservice.model.OrderModel;
import com.orderservice.repository.OrderRepository;
import com.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderModel saveOrder(OrderModel orderModel) {
        return orderRepository.save(orderModel);
    }

    @Override
    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderModel getOrderById(String orderNumber) {
        Optional<OrderModel> orderModel = orderRepository.findById(orderNumber);
        if (orderModel.isPresent()) {
            return orderModel.get();
        }
        throw new ResourceNotFoundException("Order id is not present by the id: " + orderNumber);
    }
}
