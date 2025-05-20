package com.rahulsmgv.geoapp.repository;

import com.rahulsmgv.geoapp.entity.ConfigEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigGeoAppRepository extends MongoRepository<ConfigEntity, ObjectId> {
}
