package com.orderservice.controller;

import com.orderservice.kafka.OrderProducer;
import com.orderservice.model.OrderModel;
import com.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(
        name = "CRUD REST APIs for microservice",
        description = "CRUD REST APIs - Create Orders, Update Orders, Get Orders, Get All Orders"
)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProducer orderProducer;

//    @PostMapping("/createOrder/myntra")
    @PreAuthorize("hasAuthority('Myntra User')")
    @Operation(
            summary = "Create Myntra Order REST API",
            description = "Create Myntra Order REST API is used to save order in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping(value = "/createOrder/myntra", consumes = { MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<OrderModel> saveOrderMyntra(@Valid @RequestBody OrderModel orderModel) {
        OrderModel savedOrder = orderService.saveOrderMyntra(orderModel);
        orderProducer.sendMessage(savedOrder);
        return new ResponseEntity<OrderModel>(savedOrder, HttpStatus.CREATED);
    }

    @PostMapping(value = "/createOrder/flipkart", consumes = { MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAuthority('Flipkart User')")
    @Operation(
            summary = "Create Flipkart Order REST API",
            description = "Create Flipkart Order REST API is used to save order in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    public ResponseEntity<OrderModel> saveOrderFlipKart(@Valid @RequestBody OrderModel orderModel) {
        OrderModel savedOrder = orderService.saveOrderFlipkart(orderModel);
        orderProducer.sendMessage(savedOrder);
        return new ResponseEntity<OrderModel>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/getAllOrders")
    @PreAuthorize("hasAuthority('Admin')")
    @Operation(
            summary = "Get All Orders REST API",
            description = "Get All Orders REST API is used to get a all the orders from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    public List<OrderModel> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/OrderById/{orderNumber}")
    @PreAuthorize("hasAuthority('Admin')")
    @Operation(
            summary = "Get Order By ID REST API",
            description = "Get Order By ID REST API is used to get a single order from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("orderNumber") String orderNumber) {
        return new ResponseEntity<OrderModel>(orderService.getOrderById(orderNumber), HttpStatus.OK);
    }

    @PutMapping("/updateOrder/{orderNumber}")
    @PreAuthorize("hasAuthority('Admin')")
    @Operation(
            summary = "Update Order REST API",
            description = "Update Order REST API is used to update a particular order in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    public ResponseEntity<OrderModel> updateOrder(@RequestBody OrderModel orderModel, @PathVariable("orderNumber") String orderNumber) {
        OrderModel updateOrder = orderService.updateOrder(orderModel,orderNumber);
        orderProducer.sendMessage(orderService.updateOrder(orderModel, orderNumber));
        return new ResponseEntity<OrderModel>(updateOrder, HttpStatus.OK);
    }
}
