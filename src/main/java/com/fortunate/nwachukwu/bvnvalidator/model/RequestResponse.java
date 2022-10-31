package com.fortunate.nwachukwu.bvnvalidator.model;

import com.fortunate.nwachukwu.bvnvalidator.dtos.BvnRequest;
import com.fortunate.nwachukwu.bvnvalidator.dtos.BvnResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "RequestResponse")
public class RequestResponse {
    @Id
    private Long id;
    private BvnRequest request;
    private BvnResponse bvnResponse;


    public RequestResponse(BvnRequest request, BvnResponse bvnResponse){
        this.request = request;
        this.bvnResponse = bvnResponse;
    }

}
