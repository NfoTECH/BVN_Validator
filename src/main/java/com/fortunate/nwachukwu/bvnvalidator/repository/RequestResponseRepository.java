package com.fortunate.nwachukwu.bvnvalidator.repository;

import com.fortunate.nwachukwu.bvnvalidator.model.RequestResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestResponseRepository extends MongoRepository<RequestResponse, Long> {

}
