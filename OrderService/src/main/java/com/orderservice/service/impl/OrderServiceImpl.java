package com.orderservice.service.impl;

import com.orderservice.exception.ResourceNotFoundException;
import com.orderservice.model.OrderModel;
import com.orderservice.repository.OrderRepository;
import com.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderModel saveOrderMyntra(OrderModel orderModel) {
        String orderNumber = generateOrderNumberForMyntra();
        orderModel.setOrderNumber(orderNumber);
        orderModel.setStatus("CREATED");
        orderModel.setSource("Myntra");
        orderModel.setCreatedAt(new Date());
        return orderRepository.save(orderModel);
    }

    private String generateOrderNumberForMyntra() {
        int count = (int) orderRepository.count();
        return "MYN" + String.format("%03d", count+1);
    }
    private String generateOrderNumberForFlipkart() {
        int count = (int) orderRepository.count();
        return "FLP" + String.format("%03d", count+1);
    }
    @Override
    public OrderModel saveOrderFlipkart(OrderModel orderModel) {
        String orderNumber = generateOrderNumberForFlipkart();
        orderModel.setOrderNumber(orderNumber);
        orderModel.setStatus("CREATED");
        orderModel.setSource("Flipkart");
        orderModel.setCreatedAt(new Date());
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

    @Override
    public OrderModel updateOrder(OrderModel orderModel, String orderNumber) {
        OrderModel existingOrder = getOrderById(orderNumber);
        existingOrder.setCustName(orderModel.getCustName() != null ? orderModel.getCustName() : existingOrder
                .getCustName());
        existingOrder.setAddress(orderModel.getAddress() != null ? orderModel.getAddress() : existingOrder
                .getAddress());
        existingOrder.setCity(orderModel.getCity() != null ? orderModel.getCity() : existingOrder
                .getCity());
        existingOrder.setLocality(orderModel.getLocality() != null ? orderModel.getLocality() : existingOrder
                .getLocality());
        existingOrder.setState(orderModel.getState() != null ? orderModel.getState() : existingOrder
                .getState());
        existingOrder.setEmail(orderModel.getEmail() != null ? orderModel.getEmail() : existingOrder
                .getEmail());
        existingOrder.setZipcode(orderModel.getZipcode() != null ? orderModel.getZipcode() : existingOrder
                .getZipcode());
        existingOrder.setCountry(orderModel.getCountry() != null ? orderModel.getCountry() : existingOrder
                .getCountry());
        existingOrder.setMobile(orderModel.getMobile() != null ? orderModel.getMobile() : existingOrder
                .getMobile());
        existingOrder.setOrderLineEntries(orderModel.getOrderLineEntries() != null ? orderModel.getOrderLineEntries() : existingOrder
                .getOrderLineEntries());
        return orderRepository.save(existingOrder);
    }
}
