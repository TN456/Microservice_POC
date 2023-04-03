package com.shipmentservice.service;

import com.shipmentservice.model.ShipmentModel;

import java.util.List;

public interface ShipmentService {
    ShipmentModel saveShipment(ShipmentModel shipmentModel);
    List<ShipmentModel> getAllShipment();
    ShipmentModel getShipmentById(String shipmentNumber);


}
