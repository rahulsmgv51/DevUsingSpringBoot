package com.rahulsmgv.geoapp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rahulsmgv.geoapp.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId>{
	UserEntity findByUserName(String username);
}
