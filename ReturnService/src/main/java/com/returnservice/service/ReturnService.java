package com.returnservice.service;


import com.returnservice.model.ReturnModel;

import java.util.List;

public interface ReturnService {



    ReturnModel saveMyntraReturn(ReturnModel returnModel);
    ReturnModel saveFlipkartReturn(ReturnModel returnModel);

    List<ReturnModel> getAllReturnOrder();

    ReturnModel getReturnOrderById(String returnOrderNumber);




}
