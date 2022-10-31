package com.fortunate.nwachukwu.bvnvalidator.service;

import com.fortunate.nwachukwu.bvnvalidator.dtos.BvnRequest;
import com.fortunate.nwachukwu.bvnvalidator.dtos.BvnResponse;
import com.fortunate.nwachukwu.bvnvalidator.model.Bvn;
import com.fortunate.nwachukwu.bvnvalidator.model.RequestResponse;
import com.fortunate.nwachukwu.bvnvalidator.repository.BvnRepository;
import com.fortunate.nwachukwu.bvnvalidator.repository.RequestResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class BvnService {
    BvnRepository bvnRepository;
    RequestResponseRepository requestResponseRepository;
    @Autowired
    public BvnService(BvnRepository bvnRepository, RequestResponseRepository requestResponseRepository) {
        this.bvnRepository = bvnRepository;
        this.requestResponseRepository = requestResponseRepository;
    }


    public BvnResponse validateBvn(BvnRequest bvnRequest) {
        String actualBvn = bvnRequest.getBvn();
        BvnResponse bvnResponse = null;
        if(actualBvn ==null){//Empty BVN in request payload
            bvnResponse = new BvnResponse("One or more of your request parameters failed validation. Please retry", 400, null);
        }
        else{
            boolean isMatch = Pattern.compile("^\\d+$")
                    .matcher(actualBvn)
                    .find();
            if(isMatch){
                if(actualBvn.length() < 11 ){//invalid length
                    bvnResponse = new BvnResponse("The searched BVN is invalid",02, actualBvn);
                }
                else {
                    Bvn bvn = bvnRepository.findByBvn(actualBvn).orElse(null);
                    if(bvn == null){
                        bvnResponse = new BvnResponse(" “The searched BVN does not exist",01, actualBvn);
                    }
                    else{
                        bvnResponse = new BvnResponse("Success", 00, actualBvn, bvn.getImageDetails(), bvn.toString());
                    }
                    //Valid response
                }
            }
            else{//Invalid BVN (Contains non digits) in request payload
                bvnResponse = new BvnResponse("The searched BVN is invalid", 400,actualBvn);
            }
        }
        Long count = Long.valueOf(requestResponseRepository.findAll().size());
        if(count == null){
            count = 0L;
        }
        RequestResponse requestResponse = new RequestResponse(count+1, bvnRequest, bvnResponse);
        requestResponseRepository.save(requestResponse);
        return bvnResponse;
    }
}
