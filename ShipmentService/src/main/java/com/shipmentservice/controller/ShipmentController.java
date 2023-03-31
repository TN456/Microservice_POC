package com.shipmentservice.controller;

import com.shipmentservice.model.ShipmentModel;
import com.shipmentservice.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class ShipmentController {

    @Autowired
    private ShipmentRepository shipmentRepository;
    @PostMapping("/addShipment")
    public ResponseEntity<ShipmentModel> saveShipment(@RequestBody ShipmentModel shipmentModel){
        return new ResponseEntity<ShipmentModel>(shipmentRepository.save(shipmentModel), HttpStatus.CREATED);
    }

}
