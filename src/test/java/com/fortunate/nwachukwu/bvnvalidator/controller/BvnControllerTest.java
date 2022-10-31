package com.fortunate.nwachukwu.bvnvalidator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortunate.nwachukwu.bvnvalidator.dtos.BvnRequest;
import com.fortunate.nwachukwu.bvnvalidator.dtos.BvnResponse;
import com.fortunate.nwachukwu.bvnvalidator.model.Bvn;
import com.fortunate.nwachukwu.bvnvalidator.service.BvnService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BvnController.class})
class BvnControllerTest {
    @MockBean
    BvnService bvnService;
    @Autowired
    BvnController bvnController;

    Bvn bvn = new Bvn(1L, "12345678901", "Fortunate", "Nwachukwu",
            'M', "https://res.cloudinary.com/olamire/image/upload/v1590000000/olamire.jpg");
    @Test
    void postRequest() throws Exception {
        BvnRequest happyBvnRequest = new BvnRequest("12345678901");
        String content = (new ObjectMapper()).writeValueAsString(happyBvnRequest);
        BvnResponse happyBvnResponse = new BvnResponse("BVN is valid", 00, happyBvnRequest.getBvn(), bvn.getImageDetails(), bvn.toString());
        when(bvnService.validateBvn(happyBvnRequest)).thenReturn(happyBvnResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/bv-service/svalidate/wrapper").
                contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(bvnController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.content().string((new ObjectMapper()).writeValueAsString(happyBvnResponse)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        BvnRequest sadBvnRequest = new BvnRequest("1234567891");
        content = (new ObjectMapper()).writeValueAsString(sadBvnRequest);
        BvnResponse sadBvnResponse = new BvnResponse("BVN is valid", 00, happyBvnRequest.getBvn(), bvn.getImageDetails(), bvn.toString());
        when(bvnService.validateBvn(sadBvnRequest)).thenReturn(sadBvnResponse);
        requestBuilder = MockMvcRequestBuilders.post("/bv-service/svalidate/wrapper").
                contentType(MediaType.APPLICATION_JSON)
                .content(content);
        actualPerformResult = MockMvcBuilders.standaloneSetup(bvnController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.content().string((new ObjectMapper()).writeValueAsString(sadBvnResponse)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}