package com.returnservice.controller;

import com.returnservice.kafkaconsumer.returnProducer;
import com.returnservice.model.ReturnModel;
import com.returnservice.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/return")
@RestController
public class ReturnController {

    @Autowired
    private ReturnService returnService;
    @Autowired
    private returnProducer returnproducer;


    @PostMapping("/createReturn/myntra")
    @PreAuthorize("hasAuthority('Myntra User')")
    public ResponseEntity<ReturnModel> saveMyntraReturn(@Valid @RequestBody ReturnModel returnModel ) {
        ReturnModel savedReturn = returnService.saveMyntraReturn(returnModel);
        returnproducer.sendMessage(savedReturn);
        return new ResponseEntity<ReturnModel>(savedReturn, HttpStatus.CREATED);
    }

    @PostMapping("/createReturn/flipkartFlipkart")
    @PreAuthorize("hasAuthority('Flipkart User')")
    public ResponseEntity<ReturnModel> saveReturn(@Valid @RequestBody ReturnModel returnModel ) {
        ReturnModel savedReturn = returnService.saveFlipkartReturn(returnModel);
        returnproducer.sendMessage(savedReturn);
        return new ResponseEntity<ReturnModel>(savedReturn, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('Admin')")
    public List<ReturnModel> getAllReturnOrder(){
        return returnService.getAllReturnOrder();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("{returnOrderNumber}")
    public ResponseEntity<ReturnModel> getReturnOrderById(@PathVariable("returnOderNumber") String returnOrderNumber){
        return new ResponseEntity<ReturnModel>(returnService.getReturnOrderById(returnOrderNumber), HttpStatus.OK);
    }

    @PutMapping("/updateReturn/{returnOrderNumber}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ReturnModel> updateReturn(@RequestBody ReturnModel returnModel , @PathVariable("returnOrderNumber") String returnOrderNumber){
        return new ResponseEntity<ReturnModel>(returnService.updateReturn(returnModel, returnOrderNumber) ,HttpStatus.OK);
    }


}
