package com.shipmentservice.model;


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

@Document(collection = "Shipment")
public class ShipmentModel {

    @Id
    private String shipmentNumber;
    private String custName;
    private String address;
    private String city;
    private String locality;
    private String state;
    private String email;
    private String zipcode;
    private String country;
    private String mobile;
    private String orderNumber;
    private String source;
    @CreatedDate
    private Date shippedOn;

    @Data
    private static class ShipmentLineEntries{
        private String itemId;
        private String itemDescription;
        private int quantity;
    }
    private List<ShipmentLineEntries> shipmentLineEntries;
}
