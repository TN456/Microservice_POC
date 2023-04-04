package com.shipmentservice.controller;

import com.shipmentservice.model.ShipmentModel;
import com.shipmentservice.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")

public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping("/addShipment")
    public ResponseEntity<ShipmentModel> saveShipment(@Valid @RequestBody ShipmentModel shipmentModel){
        return new ResponseEntity<ShipmentModel>(shipmentService.saveShipment(shipmentModel), HttpStatus.CREATED);
    }

    @GetMapping("/shipment/all")
    public List<ShipmentModel> getAllShipment(){
        return shipmentService.getAllShipment();
    }

    @GetMapping("/shipment/{shipmentNumber}")
    public ResponseEntity<ShipmentModel> getShipmentById(@PathVariable("shipmentNumber") String shipmentNumber){
        return new ResponseEntity<ShipmentModel>(shipmentService.getShipmentById(shipmentNumber), HttpStatus.OK);
    }

}
