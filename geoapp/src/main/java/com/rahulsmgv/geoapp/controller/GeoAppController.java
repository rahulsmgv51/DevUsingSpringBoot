package com.rahulsmgv.geoapp.controller;

import java.time.Clock;
import java.util.List;
import java.util.Optional;
import java.awt.desktop.UserSessionEvent;
import java.lang.ProcessHandle.Info;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
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
import com.rahulsmgv.geoapp.repository.GeoAppRepository;
import com.rahulsmgv.geoapp.service.GeoAppService;
import com.rahulsmgv.geoapp.service.UserService;

import ch.qos.logback.core.status.InfoStatus;
import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
@Slf4j
@RestController
@RequestMapping("/geo/app")
public class GeoAppController {
	
	@Autowired
	private GeoAppService geoAppService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all/{userName}")
	public ResponseEntity<GeoAppEntity> getAllGeoAppEntriesofUser(@PathVariable String userName){
		log.info("Request Received from User for GetAll Details_"+ userName);
		UserEntity userEntity = userService.findByUsername(userName);
		log.info("Request Sent for finding user details by Username "+userName);
		List<GeoAppEntity> userGeoAppEntities = userEntity.getGeoappEntities();
		log.info("Response Sent to User of GetAll Details" + userGeoAppEntities);
		if(userGeoAppEntities != null && !userGeoAppEntities.isEmpty()) {
			return new ResponseEntity(userGeoAppEntities, HttpStatus.OK);
		}
		return new ResponseEntity<GeoAppEntity>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/create/{userName}")
	public ResponseEntity<GeoAppEntity> createEntity(@RequestBody GeoAppEntity createEntity, @PathVariable String userName) {
		log.info("Request Received from User for Create a new Entity"+createEntity);
		try {
			geoAppService.saveEntry(createEntity, userName);
			log.info("Response Sent to User of Newly Created Entity");
			return new ResponseEntity<GeoAppEntity>(createEntity, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<GeoAppEntity>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/id/{geoAppId}")
	public ResponseEntity<GeoAppEntity> getGeoAppEntiyById(@PathVariable ObjectId geoAppId) {
		log.info("Request Received from User for Get Details_"+geoAppId);
		Optional<GeoAppEntity> geoappEntity = geoAppService.getById(geoAppId);
		if(geoappEntity.isPresent()) {
			log.info("Response Sent to User of Get Details" + geoappEntity);
			return new ResponseEntity<>(geoappEntity.get(), HttpStatus.OK);
		}
		return new ResponseEntity<GeoAppEntity>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/id/{userName}/{geoAppId}")
	public ResponseEntity<?> deleteGeoAppById(@PathVariable ObjectId geoAppId, @PathVariable String userName) {
		log.info("Request Received from User for Remove User Details with id_"+geoAppId + " Also Received userName for delete id from geoappEntities List "+ userName);
		geoAppService.deleteById(geoAppId, userName);
		log.info("Response Sent to User of Removed User ");
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{userName}/{geoAppId}")
	public ResponseEntity<?> updateGeoAppById(@PathVariable ObjectId geoAppId, @RequestBody GeoAppEntity geoAppUpdateEntity, @PathVariable String userName) {
		log.info("Request Received from User for Update a new Entity: "+geoAppId +" and "+ geoAppUpdateEntity);
		try {
			GeoAppEntity oldData = geoAppService.getById(geoAppId).orElse(null);
			if(oldData != null) {
				oldData.setContent(geoAppUpdateEntity.getContent() != null && !geoAppUpdateEntity.getContent().isEmpty() ? geoAppUpdateEntity.getContent():oldData.getContent());
				oldData.setTitle(!geoAppUpdateEntity.getTitle().isEmpty() ? geoAppUpdateEntity.getTitle():oldData.getTitle());
				geoAppService.saveEntry(oldData);
				log.info("Response Sent to User afte update the data "+ oldData);
				return new ResponseEntity<>(oldData, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			log.info("Exception Occured While updating GeoApp Details"+e.getMessage());
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
}





