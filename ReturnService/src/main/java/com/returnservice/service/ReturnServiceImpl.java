package com.returnservice.service;

import com.returnservice.exception.ResourceNotFoundException;
import com.returnservice.model.ReturnModel;
import com.returnservice.repository.ReturnRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService{

    private ReturnRepository returnRepository;
    public ReturnServiceImpl(ReturnRepository returnRepository) {
        this.returnRepository = returnRepository;
    }



    @Override
    public ReturnModel saveReturnModel(ReturnModel returnModel) {

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
}
