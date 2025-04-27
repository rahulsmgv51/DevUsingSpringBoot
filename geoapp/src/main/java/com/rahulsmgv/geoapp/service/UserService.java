package com.rahulsmgv.geoapp.service;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rahulsmgv.geoapp.entity.UserEntity;
import com.rahulsmgv.geoapp.repository.UserRepository;

@Component
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity saveEntry(UserEntity userEntity) {
		userRepository.save(userEntity);
		return userEntity;
	}
	
	public List<UserEntity> getAll(){
		return userRepository.findAll();
	}
	
	public Optional<UserEntity> getById(ObjectId id) {
		return userRepository.findById(id);
	}
	
	public void deleteById(ObjectId id) {
		userRepository.deleteById(id);
	}
	
	public UserEntity findByUsername(String username) {
		return userRepository.findByUserName(username);
	}
}
