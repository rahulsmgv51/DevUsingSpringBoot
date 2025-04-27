package com.rahulsmgv.geoapp.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rahulsmgv.geoapp.entity.GeoAppEntity;
import com.rahulsmgv.geoapp.entity.UserEntity;
import com.rahulsmgv.geoapp.repository.UserRepository;
import com.rahulsmgv.geoapp.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/geo/app/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<UserEntity> getAll(){
		log.info("Request Received from User for GetAll Details");
		List<UserEntity> userEntities = userService.getAll();
		log.info("Response Sent to User of GetAll Details" + userEntities);
		if(!userEntities.isEmpty()) {
			return new ResponseEntity(userEntities, HttpStatus.OK);
		}
		return new ResponseEntity<UserEntity>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/create")
	public ResponseEntity<UserEntity> createEntity(@RequestBody UserEntity createEntity) {
		log.info("Request Received from User for Create a new Entity"+createEntity.toString());
		try {
			UserEntity userEntity = userService.saveEntry(createEntity);
			log.info("Response Sent to User of Newly Created Entity" + userEntity.toString());
			return new ResponseEntity<UserEntity>(userEntity, HttpStatus.CREATED);
		} catch (Exception e) {
			log.info("Exception Occured while insert data in users table "+ e.getMessage());
			return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/id/{userId}")
	public ResponseEntity<UserEntity> getGeoAppEntiyById(@PathVariable ObjectId userId) {
		log.info("Request Received from User for Get Details_"+userId);
		Optional<UserEntity> geoappEntity = userService.getById(userId);
		if(geoappEntity.isPresent()) {
			log.info("Response Sent to User of Get Details" + geoappEntity);
			return new ResponseEntity<>(geoappEntity.get(), HttpStatus.OK);
		}
		return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/id/{userId}")
	public ResponseEntity<?> deleteGeoAppById(@PathVariable ObjectId userId) {
		log.info("Request Received from User for Remove User Details with id_"+userId);
		userService.deleteById(userId);
		log.info("Response Sent to User of Removed User ");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateGeoAppById(@RequestBody UserEntity userEntity) {
		log.info("Request Received from User for Update a new Entity: "+ userEntity);
		try {
			UserEntity userentityInDB = userService.findByUsername(userEntity.getUserName());
			if(userentityInDB != null) {
				userentityInDB.setUserName(userEntity.getUserName());
				userentityInDB.setPassword(userEntity.getPassword());
				userService.saveEntry(userentityInDB);
				log.info("Response Sent to User After updateting his/her password Successfully !!!");
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}
			
		} catch (Exception e) {
			log.info("Exception Occured while updating userpassword "+ e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
