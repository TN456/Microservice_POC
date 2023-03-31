package com.returnservice.controller;

import com.returnservice.model.ReturnModel;
import com.returnservice.service.ReturnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RequestMapping("/api/return")
@RestController
public class ReturnController {

    private ReturnService returnService;

    public ReturnController(ReturnService returnService){
        this.returnService=returnService;
    }

    // build create employee REST API
    @PostMapping("/create")
    public ResponseEntity<ReturnModel> saveReturnModel(@RequestBody ReturnModel returnModel){
        return new ResponseEntity<ReturnModel>(returnService.saveReturnModel(returnModel), HttpStatus.CREATED );
    }
}
