package com.returnservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
//import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection="return")
public class ReturnModel {

    @Id
    private String returnOrderNumber;
    private String orderNumber;
    private String customName;
    private String address;
    private String city;
    private String locality;
    private String state;
    private String email;
    private String zipcode;
    private String country;
    private String mobile;

    private String source;
    @CreatedDate
    private Date returnON;


    private List<ReturnLineEntries> returnLineEntries;


    @Data
    public static class ReturnLineEntries{
        private String itemId;
        private String itemDescription;
        private int quantity;
        private String reason;
    }


}