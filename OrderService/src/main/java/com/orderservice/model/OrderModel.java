package com.orderservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Orders")
@Schema(
        description = "Order Model Information"
)
public class OrderModel {

    @Id
    @Schema(description = "Order Number")
    private String orderNumber;
    @NotNull(message = "Customer name cannot be empty")
    @Schema(description = "Customer Name")
    private String custName;
    @NotNull(message = "Customer address cannot be empty")
    @Schema(description = "Customer Address")
    private String address;
    @Schema(description = "Customer City")
    private String city;
    @Schema(description = "Customer Locality")
    private String locality;
    @Schema(description = "Customer State")
    private String state;
    @NotNull(message = "Customer email cannot be empty")
    @Email(message = "Valid email id should be provided")
    @Schema(description = "Customer Email")
    private String email;
    @Schema(description = "Customer Zipcode")
    private String zipcode;
    @Schema(description = "Customer Country")
    private String country;
    @NotNull(message = "Customer mobile number cannot be empty")
    @Schema(description = "Customer Mobile number")
    @Size(max = 10,message = "Mobile number should be of 10 digits")
    private String mobile;
    @Schema(description = "Customer Payment Method")
    private String paymentMethod;
    @Schema(description = "Customer Status")
    private String status;
    @Schema(description = "Customer Source")
    private String source;

    @CreatedDate
    @Schema(description = "Order created Time")
    private Date createdAt;

    @LastModifiedDate
    @Schema(description = "Order updated time")
    private Date updatedAt;

    private List<orderLineEntries> orderLineEntries;

    @Data
    public static class orderLineEntries {
        @Schema(description = "Item ID")
        @NotNull(message = "Item id cannot be empty")
        private String itemId;
        @Schema(description = "Item Description")
        private String itemDescription;
        @Schema(description = "Item price")
        @NotNull(message = "Item price cannot be empty")
        private double price;
        @NotNull(message = "Quantity cannot be empty")
        @Schema(description = "Item Quantity")
        @Size(min = 1)
        private int quantity;
    }

}
