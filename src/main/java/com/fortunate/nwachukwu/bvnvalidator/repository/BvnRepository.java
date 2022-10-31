package com.fortunate.nwachukwu.bvnvalidator.repository;

import com.fortunate.nwachukwu.bvnvalidator.model.Bvn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BvnRepository extends MongoRepository<Bvn, Long> {
    Optional<Bvn> findByBvn(String bvn);
}
