package com.returnservice.controller;

import com.returnservice.model.ReturnModel;
import com.returnservice.service.ReturnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

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

    @GetMapping
    public List<ReturnModel> getAllReturnOrder(){
        return returnService.getAllReturnOrder();
    }

    @GetMapping("{returnOrderNumber}")
    public ResponseEntity<ReturnModel> getReturnOrderById(@PathVariable("returnOderNumber") String returnOrderNumber){
        return new ResponseEntity<ReturnModel>(returnService.getReturnOrderById(returnOrderNumber), HttpStatus.OK);
    }


}
