package com.shipmentservice.service.Impl;

import com.shipmentservice.exception.ResourceNotFoundException;
import com.shipmentservice.model.ShipmentModel;
import com.shipmentservice.repository.ShipmentRepository;
import com.shipmentservice.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {


    @Autowired
    private ShipmentRepository shipmentRepository;


    @Override
    public ShipmentModel saveMyntraShipment(ShipmentModel shipmentModel) {
        shipmentModel.setSource("Myntra");
        return shipmentRepository.save(shipmentModel);
    }
    @Override
    public ShipmentModel saveFlipkartShipment(ShipmentModel shipmentModel) {
        shipmentModel.setSource("Flipkart");
        return shipmentRepository.save(shipmentModel);
    }

    @Override
    public List<ShipmentModel> getAllShipment() {
        return shipmentRepository.findAll();
    }

    @Override
    public ShipmentModel getShipmentById(String shipmentNumber) {
        return shipmentRepository.findById(shipmentNumber).orElseThrow(()-> new ResourceNotFoundException("Shipment number not found "+shipmentNumber));
    }
    @Override
    public ShipmentModel updateShipment(ShipmentModel shipmentModel, String shipmentNumber) {
        //get existing doc from db
        //populate new value from request to existing object
        ShipmentModel existingShipment = shipmentRepository.findById(shipmentModel.getShipmentNumber()).get();
        existingShipment.setCountry(shipmentModel.getCountry());
        existingShipment.setState(shipmentModel.getState());
        existingShipment.setCity(shipmentModel.getCity());
        existingShipment.setLocality(shipmentModel.getLocality());
        existingShipment.setAddress(shipmentModel.getAddress());
        existingShipment.setZipcode(shipmentModel.getZipcode());
        existingShipment.setUpdatedShipmentOn(shipmentModel.getUpdatedShipmentOn());
        existingShipment.setShipmentLineEntries(shipmentModel.getShipmentLineEntries());
        return shipmentRepository.save(existingShipment);
    }

}
