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
    public OrderModel saveOrderMyntra(OrderModel orderModel) {
        orderModel.setStatus("CREATED");
        orderModel.setSource("Myntra");
        return orderRepository.save(orderModel);
    }

    @Override
    public OrderModel saveOrderFlipkart(OrderModel orderModel) {
        orderModel.setStatus("CREATED");
        orderModel.setSource("Flipkart");
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
        OrderModel exsistingOrder = getOrderById(orderNumber);
        exsistingOrder.setCustName(orderModel.getCustName() != null ? orderModel.getCustName() : exsistingOrder
                .getCustName());
        exsistingOrder.setAddress(orderModel.getAddress() != null ? orderModel.getAddress() : exsistingOrder
                .getAddress());
        exsistingOrder.setCity(orderModel.getCity() != null ? orderModel.getCity() : exsistingOrder
                .getCity());
        exsistingOrder.setLocality(orderModel.getLocality() != null ? orderModel.getLocality() : exsistingOrder
                .getLocality());
        exsistingOrder.setState(orderModel.getState() != null ? orderModel.getState() : exsistingOrder
                .getState());
        exsistingOrder.setEmail(orderModel.getEmail() != null ? orderModel.getEmail() : exsistingOrder
                .getEmail());
        exsistingOrder.setZipcode(orderModel.getZipcode() != null ? orderModel.getZipcode() : exsistingOrder
                .getZipcode());
        exsistingOrder.setCountry(orderModel.getCountry() != null ? orderModel.getCountry() : exsistingOrder
                .getCountry());
        exsistingOrder.setMobile(orderModel.getMobile() != null ? orderModel.getMobile() : exsistingOrder
                .getMobile());
        exsistingOrder.setOrderLineEntries(orderModel.getOrderLineEntries() != null ? orderModel.getOrderLineEntries() : exsistingOrder
                .getOrderLineEntries());
        return orderRepository.save(exsistingOrder);
    }
}
