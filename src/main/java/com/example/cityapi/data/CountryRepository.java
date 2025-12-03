package com.example.cityapi.data;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends MongoRepository<Country, ObjectId> {

    Country findByNameIgnoreCase(String name);

}
