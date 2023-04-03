package com.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Orders")
public class OrderModel {

    @Id
    private String orderNumber;
    private String custName;
    private String address;
    private String city;
    private String locality;
    private String state;
    private String email;
    private String zipcode;
    private String country;
    private String mobile;
    private String paymentMethod;
    private String source;

    @CreatedDate
    private Date createdAt;

    private List<orderLineEntries> orderLineEntries;

    @Data
    public static class orderLineEntries {
        private String itemId;
        private String itemDescription;
        private double price;
        private int quantity;
    }

}
