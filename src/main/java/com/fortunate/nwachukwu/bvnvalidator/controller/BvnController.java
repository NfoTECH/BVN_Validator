package com.fortunate.nwachukwu.bvnvalidator.controller;

import com.fortunate.nwachukwu.bvnvalidator.dtos.BvnRequest;
import com.fortunate.nwachukwu.bvnvalidator.dtos.BvnResponse;
import com.fortunate.nwachukwu.bvnvalidator.service.BvnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bv-service")
public class BvnController {
    @Autowired
    private BvnService bvnService;
    @PostMapping("/svalidate/wrapper")
    public BvnResponse postRequest(@RequestBody BvnRequest bvnRequest) {
        return bvnService.validateBvn(bvnRequest);
    }


}
