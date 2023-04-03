package com.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Orders")
public class OrderModel {

    @Id
    private String orderNumber;
    @NotNull(message = "Customer name cannot be empty")
    private String custName;
    @NotNull(message = "Customer address cannot be empty")
    private String address;
    private String city;
    private String locality;
    private String state;
    @NotNull(message = "Customer email cannot be empty")
    private String email;
    private String zipcode;
    private String country;
    @NotNull(message = "Customer mobile number cannot be empty")
    private String mobile;
    private String paymentMethod;
    @NotNull(message = "source cannot be empty")
    private String source;

    @CreatedDate
    private Date createdAt;

    private List<orderLineEntries> orderLineEntries;

    @Data
    public static class orderLineEntries {
        @NotNull(message = "Item id cannot be empty")
        private String itemId;
        private String itemDescription;
        @NotNull(message = "Item price cannot be empty")
        private double price;
        @NotNull(message = "Quantity cannot be empty")
        private int quantity;
    }

}
