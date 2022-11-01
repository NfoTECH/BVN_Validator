package com.fortunate.nwachukwu.bvnvalidator.controller;

import com.fortunate.nwachukwu.bvnvalidator.dtos.BvnRequest;
import com.fortunate.nwachukwu.bvnvalidator.dtos.BvnResponse;
import com.fortunate.nwachukwu.bvnvalidator.service.BvnService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/bv-service")
public class BvnController {
    private final BvnService bvnService;
    @PostMapping("/svalidate/wrapper")
    public BvnResponse postRequest(@RequestBody BvnRequest bvnRequest) {
        return bvnService.validateBvn(bvnRequest);
    }


}
