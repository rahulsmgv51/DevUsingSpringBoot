package com.rahulsmgv.geoapp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.rahulsmgv.geoapp.entity.UserEntity;
import com.rahulsmgv.geoapp.repository.UserRepository;

@Component
public class UserService {
	@Autowired
	private UserRepository userRepository;

//	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public UserEntity saveEntry(UserEntity userEntity) {
		userRepository.save(userEntity);
		return userEntity;
	}

	public boolean saveNewEntry(UserEntity userEntity) {
		try {
			userRepository.save(userEntity);
			return true;
		}catch (Exception e){
			return false;
		}
	}

//	public UserEntity saveNewEntry(UserEntity userEntity) {
//		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
//		userEntity.setRoles(Arrays.asList("USER"));
//		userRepository.save(userEntity);
//		return userEntity;
//	}
	
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
