package com.rahulsmgv.geoapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rahulsmgv.geoapp.entity.GeoAppEntity;
import com.rahulsmgv.geoapp.entity.UserEntity;
import com.rahulsmgv.geoapp.repository.GeoAppRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GeoAppService {
	@Autowired
	private GeoAppRepository geoAppRepository;
	@Autowired
	private UserService userService;
	
	public void saveEntry(GeoAppEntity geoAppEntity, String userName) {
		log.info("Request Sent to User Details Service for finding the data of a specific user_"+userName);;
		UserEntity userEntity = userService.findByUsername(userName);
		log.info("Response Received from User Details Service of a specific user details "+userEntity.toString());
		
		log.info("Request Sent to GeoApp Entity Service for insert new a Record_"+ geoAppEntity.toString());
		geoAppEntity.setDate(LocalDateTime.now());
		GeoAppEntity saved = geoAppRepository.save(geoAppEntity);
		log.info("Response Received From GeoApp Entity Service After Inserting a new Record_"+saved.toString());
		
		log.info("Request Sent to UserEntity Service for add record in geoappEntities List_"+saved.toString());
		userEntity.getGeoappEntities().add(saved);
		log.info("Response Received from UserEnitity Service after adding record in geoappEntities List");
		
		log.info("Request Sent to UserEntity Service for update user details after adding record in geoappEntities List");
		userService.saveEntry(userEntity);
	}
	
	public void saveEntry(GeoAppEntity geoAppEntity) {
		log.info("Request Sent to UserEntity Service for update user details after adding record in geoappEntities List");
		geoAppRepository.save(geoAppEntity);
	}
	
	public List<GeoAppEntity> getAll(){
		return geoAppRepository.findAll();
	}
	
	public Optional<GeoAppEntity> getById(ObjectId id) {
		return geoAppRepository.findById(id);
	}
	
	public void deleteById(ObjectId id, String userName) {
		log.info("Request Sent to User Details Service for finding the data of a specific user_"+userName);;
		UserEntity userEntity = userService.findByUsername(userName);
		log.info("Response Received from User Details Service of a specific user details "+userEntity.toString());
		
		log.info("Request Sent to UserEntity Service for Remove Record From geoappEntities List of Id_"+id.toString());
		userEntity.getGeoappEntities().removeIf(x -> x.getId().equals(id));
		log.info("Response Received From UserEntity Service After Remove Record From geoappEntities List");
		
		log.info("Request Sent to UserEntity Service for update record in geoappEntities List_"+userEntity.toString());
		userService.saveEntry(userEntity);
		log.info("Response Received from UserEnitity Service after Updating record in geoappEntities List");
		
		log.info("Request Sent to GeoAppEntity Service for Remove details of the given id_"+id);
		geoAppRepository.deleteById(id);
	}
}


// controller ---> service --->> repository