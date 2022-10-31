package com.fortunate.nwachukwu.bvnvalidator;

import com.fortunate.nwachukwu.bvnvalidator.model.Bvn;
import com.fortunate.nwachukwu.bvnvalidator.repository.BvnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BvnValidatorApplication implements CommandLineRunner {

    @Autowired
    private BvnRepository bvnRepository;

    public static void main(String[] args) {
        SpringApplication.run(BvnValidatorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       List<Bvn> bvnDataList = new ArrayList<>();
         bvnDataList.add(new Bvn(1L, "12345678901", "Fortunate", "Nwachukwu", 'M',
                 "https://res.cloudinary.com/olamire/image/upload/v1590000000/olamire.jpg"));
            bvnDataList.add(new Bvn(2L, "12345678902", "Fortunate", "Nwachukwu", 'M', "https://res.cloudinary.com/olamire/image/upload/v1590000000/olamire.jpg"));
            bvnDataList.add(new Bvn(3L, "12345678903", "Fortunate", "Nwachukwu", 'M', "https://res.cloudinary.com/olamire/image/upload/v1590000000/olamire.jpg"));
            bvnDataList.add(new Bvn(4L, "12345678904", "Fortunate", "Nwachukwu", 'M', "https://res.cloudinary.com/olamire/image/upload/v1590000000/olamire.jpg"));
            bvnDataList.add(new Bvn(5L, "12345678905", "Fortunate", "Nwachukwu", 'M', "https://res.cloudinary.com/olamire/image/upload/v1590000000/olamire.jpg"));
            bvnRepository.saveAll(bvnDataList);
    }
}
