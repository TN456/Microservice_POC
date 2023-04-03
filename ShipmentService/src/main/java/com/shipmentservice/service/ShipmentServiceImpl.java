package com.shipmentservice.service;

import com.shipmentservice.exception.ShipmentException;
import com.shipmentservice.model.ShipmentModel;
import com.shipmentservice.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentServiceImpl implements ShipmentService{

    @Autowired
    private ShipmentRepository shipmentRepository;


    @Override
    public ShipmentModel saveShipment(ShipmentModel shipmentModel) {
        return shipmentRepository.save(shipmentModel);
    }

    @Override
    public List<ShipmentModel> getAllShipment() {
        return shipmentRepository.findAll();
    }

    @Override
    public ShipmentModel getShipmentById(String shipmentNumber) {
        return shipmentRepository.findById(shipmentNumber).orElseThrow(()-> new ShipmentException("ShipmentModel","shipmentNumber",shipmentNumber));
    }


}
