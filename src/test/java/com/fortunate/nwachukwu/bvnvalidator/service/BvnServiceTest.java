package com.fortunate.nwachukwu.bvnvalidator.service;

import com.fortunate.nwachukwu.bvnvalidator.dtos.BvnRequest;
import com.fortunate.nwachukwu.bvnvalidator.dtos.BvnResponse;
import com.fortunate.nwachukwu.bvnvalidator.model.Bvn;
import com.fortunate.nwachukwu.bvnvalidator.repository.BvnRepository;
import com.fortunate.nwachukwu.bvnvalidator.repository.RequestResponseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class BvnServiceTest extends Mockito {
    @Autowired
    BvnRepository bvnRepository =mock(BvnRepository.class);
    @Autowired
    RequestResponseRepository requestResponseRepository = mock(RequestResponseRepository.class);

    Bvn bvn = new Bvn(1L, "12345678901", "Fortunate", "Nwachukwu", 'M', "https://res.cloudinary.com/olamire/image/upload/v1590000000/olamire.jpg");

    @Test
    void validateBvn() {
        BvnRequest bvnRequest = new BvnRequest();
        bvnRequest.setBvn("12345678901");
        when(bvnRepository.findByBvn(bvnRequest.getBvn())).thenReturn(Optional.ofNullable(bvn));
        BvnService bvnService = new BvnService(bvnRepository, requestResponseRepository);
        BvnResponse bvnResponse = bvnService.validateBvn(bvnRequest);
        assertEquals(bvnResponse.getBvn(), bvn.getBvn());
        assertEquals(bvnResponse.getImageDetail(), bvn.getImageDetails());
        assertEquals(bvnResponse.getCode(), 00);
        BvnRequest notFoundBvnRequest = new BvnRequest("12345678906");
        when(bvnRepository.findByBvn(notFoundBvnRequest.getBvn())).thenReturn(Optional.empty());
        bvnResponse = bvnService.validateBvn(notFoundBvnRequest);
        assertEquals(bvnResponse.getCode(), 01);
        BvnRequest shortBvnRequest = new BvnRequest("1234567890");
        bvnResponse = bvnService.validateBvn(shortBvnRequest);
        assertEquals(bvnResponse.getCode(), 02);
        BvnRequest nullBvnRequest = new BvnRequest();
        bvnResponse = bvnService.validateBvn(nullBvnRequest);
        assertEquals(bvnResponse.getCode(), 400);
        BvnRequest mixedBvnRequest = new BvnRequest("1234567890a");
        bvnResponse = bvnService.validateBvn(mixedBvnRequest);
        assertEquals(bvnResponse.getMessage(), "The searched BVN is invalid");

    }
}