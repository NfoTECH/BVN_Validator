package com.fortunate.nwachukwu.bvnvalidator.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;
@NoArgsConstructor
@Getter
@Setter
@Data
public class BvnResponse {
    private String Message;
    private int Code;
    private String Bvn;
    private String ImageDetail;
    private String BasicDetail;

    public BvnResponse(String message, int code, String bvn, String imageDetail, String basicDetail) {
        Message = message;
        Code = code;
        Bvn = bvn;
        ImageDetail = imageDetail;
        BasicDetail = Base64.getEncoder().encodeToString(basicDetail.getBytes());
    }

    public BvnResponse(String message, int code, String bvn) {
        Message = message;
        Code = code;
        Bvn = bvn;
    }

    public void setBasicDetail(String basicDetail) {
        BasicDetail = Base64.getEncoder().encodeToString(basicDetail.getBytes());
    }

}
