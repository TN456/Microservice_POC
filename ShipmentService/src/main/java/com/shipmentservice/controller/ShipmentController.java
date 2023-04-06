package com.shipmentservice.controller;

import com.shipmentservice.kafka.ShipmentProducer;
import com.shipmentservice.model.ShipmentModel;
import com.shipmentservice.service.ShipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "CRUD REST APIs for microservice",
        description = "CRUD REST APIs - Create Shipments, Update Shipments, Get Shipments, Get All Shipments"
)

public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ShipmentProducer shipmentProducer;

    @PostMapping("/createShipment/myntra")
    @PreAuthorize("hasAuthority('Myntra User')")
    @Operation(
            summary = "Create Myntra Shipment REST API",
            description = "Create Myntra Shipment REST API is used to save order in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    public ResponseEntity<ShipmentModel> saveMyntraShipment(@Valid @RequestBody ShipmentModel shipmentModel, HttpServletRequest request) {
        ShipmentModel savedShipment = shipmentService.saveMyntraShipment(shipmentModel);
        shipmentProducer.sendMessage(savedShipment);
        return new ResponseEntity<ShipmentModel>(savedShipment, HttpStatus.CREATED);
    }
    @PostMapping("/createShipment/flipkart")
    @PreAuthorize("hasAuthority('Flipkart User')")
    @Operation(
            summary = "Create Flipkart Shipment REST API",
            description = "Create Flipkart Shipment REST API is used to save order in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    public ResponseEntity<ShipmentModel> saveFlipkartShipment(@Valid @RequestBody ShipmentModel shipmentModel, HttpServletRequest request) {
        ShipmentModel savedShipment = shipmentService.saveFlipkartShipment(shipmentModel);
        shipmentProducer.sendMessage(savedShipment);
        return new ResponseEntity<ShipmentModel>(savedShipment, HttpStatus.CREATED);
    }

    @GetMapping("/shipment/all")
    @PreAuthorize("hasAuthority('Admin')")
    @Operation(
            summary = "Get All Shipments REST API",
            description = "Get All Orders REST API is used to get  all the Shipments from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    public List<ShipmentModel> getAllShipment(){
        return shipmentService.getAllShipment();
    }

    @GetMapping("/shipment/{shipmentNumber}")
    @PreAuthorize("hasAuthority('Admin')")
    @Operation(
            summary = "Get Shipment By ID REST API",
            description = "Get Shipment By ID REST API is used to get a single shipment from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    public ResponseEntity<ShipmentModel> getShipmentById(@PathVariable("shipmentNumber") String shipmentNumber){
        return new ResponseEntity<ShipmentModel>(shipmentService.getShipmentById(shipmentNumber), HttpStatus.OK);
    }
    @PutMapping("/updateShipment/{shipmentNumber}")
    @PreAuthorize("hasAuthority('Admin')")
    @Operation(
            summary = "Update Shipment REST API",
            description = "Update Shipment REST API is used to update a particular shipment in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    public ResponseEntity<ShipmentModel> updateShipment(@RequestBody ShipmentModel shipmentModel,@PathVariable("shipmentNumber") String shipmentNumber){
        shipmentProducer.sendMessage(shipmentService.updateShipment(shipmentModel, shipmentNumber));
        return new ResponseEntity<ShipmentModel>(shipmentService.updateShipment(shipmentModel, shipmentNumber),HttpStatus.OK);
    }

}
