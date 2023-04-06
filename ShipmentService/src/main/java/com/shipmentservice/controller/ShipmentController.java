package com.shipmentservice.controller;

import com.shipmentservice.kafka.ShipmentProducer;
import com.shipmentservice.model.ShipmentModel;
import com.shipmentservice.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")

public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ShipmentProducer shipmentProducer;

    @PostMapping("/createShipment/myntra")
    @PreAuthorize("hasAuthority('Myntra User')")
    public ResponseEntity<ShipmentModel> saveMyntraShipment(@Valid @RequestBody ShipmentModel shipmentModel, HttpServletRequest request) {
        ShipmentModel savedShipment = shipmentService.saveMyntraShipment(shipmentModel);
        shipmentProducer.sendMessage(savedShipment);
        return new ResponseEntity<ShipmentModel>(savedShipment, HttpStatus.CREATED);
    }
    @PostMapping("/createShipment/flipkart")
    @PreAuthorize("hasAuthority('Flipkart User')")
    public ResponseEntity<ShipmentModel> saveFlipkartShipment(@Valid @RequestBody ShipmentModel shipmentModel, HttpServletRequest request) {
        ShipmentModel savedShipment = shipmentService.saveFlipkartShipment(shipmentModel);
        shipmentProducer.sendMessage(savedShipment);
        return new ResponseEntity<ShipmentModel>(savedShipment, HttpStatus.CREATED);
    }

    @GetMapping("/shipment/all")
    @PreAuthorize("hasAuthority('Admin')")
    public List<ShipmentModel> getAllShipment(){
        return shipmentService.getAllShipment();
    }

    @GetMapping("/shipment/{shipmentNumber}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ShipmentModel> getShipmentById(@PathVariable("shipmentNumber") String shipmentNumber){
        return new ResponseEntity<ShipmentModel>(shipmentService.getShipmentById(shipmentNumber), HttpStatus.OK);
    }
    @PutMapping("/updateShipment/{shipmentNumber}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ShipmentModel> updateShipment(@RequestBody ShipmentModel shipmentModel,@PathVariable("shipmentNumber") String shipmentNumber){
        return new ResponseEntity<ShipmentModel>(shipmentService.updateShipment(shipmentModel, shipmentNumber) ,HttpStatus.OK);
    }

}
