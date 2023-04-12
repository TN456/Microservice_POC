package com.returnservice.service.impl;


import com.returnservice.exception.ResourceNotFoundException;
import com.returnservice.model.ReturnModel;
import com.returnservice.repository.ReturnRepository;
import com.returnservice.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnRepository returnRepository;


    @Override
    public ReturnModel saveMyntraReturn(ReturnModel returnModel) {
        String orderNumber = generateOrderNumberForMyntra();
        returnModel.setOrderNumber(orderNumber);
        returnModel.setSource("Myntra");
        returnModel.setStatus("RETURNED");
        returnModel.setReturnON(new Date());
        return returnRepository.save(returnModel);
    }

    @Override
    public ReturnModel saveFlipkartReturn(ReturnModel returnModel) {
        String orderNumber = generateOrderNumberForFlipkart();
        returnModel.setOrderNumber(orderNumber);
        returnModel.setSource("Flipkart");
        returnModel.setStatus("RETURNED");
        returnModel.setReturnON(new Date());

        return returnRepository.save(returnModel);
    }
    private String generateOrderNumberForMyntra() {
        int count = (int) returnRepository.count();
        return "MYN" + String.format("%03d", count+1);
    }
    private String generateOrderNumberForFlipkart() {
        int count = (int) returnRepository.count();
        return "FLP" + String.format("%03d", count+1);
    }

    @Override
    public List<ReturnModel> getAllReturnOrder() {
        return returnRepository.findAll();
    }

    @Override
    public ReturnModel getReturnOrderById(String returnOrderNumber) {
        return returnRepository.findById(returnOrderNumber).orElseThrow(() -> new ResourceNotFoundException("Return order number not found" + returnOrderNumber));
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
