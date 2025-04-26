package com.rahulsmgv.geoapp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rahulsmgv.geoapp.entity.GeoAppEntity;

public interface GeoAppRepository extends MongoRepository<GeoAppEntity, ObjectId>{
	
}
