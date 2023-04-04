package com.shipmentservice.model;


import io.swagger.v3.oas.annotations.media.Schema;
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

@Document(collection = "Shipment")
public class ShipmentModel {

    @Id
   // @Schema(description = "Shipment Number")
    private String shipmentNumber;
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
    @NotNull(message = "order number cannot be empty")
    private String orderNumber;
    @NotNull(message = "source cannot be empty")
    private String source;
    @CreatedDate
    private Date shippedOn;

    @Data
    private static class ShipmentLineEntries{
        @NotNull(message = "Item id cannot be empty")
        private String itemId;
        private String itemDescription;
        @NotNull(message = "Quantity cannot be empty")
        private int quantity;
    }
    private List<ShipmentLineEntries> shipmentLineEntries;
}
