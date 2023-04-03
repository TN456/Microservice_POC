package com.returnservice.service;

import com.returnservice.model.ReturnModel;

import java.util.List;

public interface ReturnService {

    public ReturnModel saveReturnModel(ReturnModel returnModel);

    List<ReturnModel> getAllReturnOrder();

    ReturnModel getReturnOrderId(String returnOrderNumber);
}
