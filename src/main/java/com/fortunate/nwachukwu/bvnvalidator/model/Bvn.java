package com.fortunate.nwachukwu.bvnvalidator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bvn")
public class Bvn {
    @Id
    private Long id;
    private String bvn;
    private String firstName;
    private String lastName;
    private Character sex;
    private String ImageDetails;

    public Bvn(String bvn){
        this.bvn = bvn;
    }
    @Override
    public String toString(){
        return firstName + " " + lastName+" "+sex+" "+bvn;
    }
}
