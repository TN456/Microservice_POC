package com.returnservice.service.impl;



import com.returnservice.exception.ResourceNotFoundException;
import com.returnservice.model.ReturnModel;
import com.returnservice.repository.ReturnRepository;
import com.returnservice.service.ReturnService;
import com.shipmentservice.model.ShipmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnRepository returnRepository;


    @Override
    public ReturnModel saveMyntraReturn(ReturnModel returnModel) {
        returnModel.setSource("Myntra");
        return returnRepository.save(returnModel);
    }

    @Override
    public ReturnModel saveFlipkartReturn(ReturnModel returnModel) {
        returnModel.setSource("Flipkart");
        return returnRepository.save(returnModel);
    }


    @Override
    public List<ReturnModel> getAllReturnOrder() {
        return returnRepository.findAll();
    }

    @Override
    public ReturnModel getReturnOrderById(String returnOrderNumber) {
        return returnRepository.findById(returnOrderNumber).orElseThrow(()-> new ResourceNotFoundException("Return order number not found" + returnOrderNumber));
    }



    public ReturnModel updateReturn(ReturnModel returnModel, String returnOrderNumber) {
        //get existing doc from db
        //populate new value from request to existing object
        ReturnModel existingReturn = returnRepository.findById(returnModel.getReturnOrderNumber()).get();
        existingReturn.setCountry(returnModel.getCountry());
        existingReturn.setState(returnModel.getState());
        existingReturn.setCity(returnModel.getCity());
        existingReturn.setLocality(returnModel.getLocality());
        existingReturn.setZipcode(returnModel.getZipcode());
        return returnRepository.save(existingReturn);
    }

}
