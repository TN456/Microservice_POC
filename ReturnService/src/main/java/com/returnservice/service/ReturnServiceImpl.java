package com.returnservice.service;

import com.returnservice.model.ReturnModel;
import com.returnservice.repository.ReturnRepository;
import org.springframework.stereotype.Service;

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
}
