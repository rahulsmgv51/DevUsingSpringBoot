package com.rahulsmgv.geoapp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rahulsmgv.geoapp.entity.GeoAppEntity;
import com.rahulsmgv.geoapp.repository.GeoAppRepository;

@Component
public class GeoAppService {
	@Autowired
	private GeoAppRepository geoAppRepository;
	
	public GeoAppEntity saveEntry(GeoAppEntity geoAppEntity) {
		geoAppRepository.save(geoAppEntity);
		return geoAppEntity;
	}
	
	public List<GeoAppEntity> getAll(){
		return geoAppRepository.findAll();
	}
	
	public Optional<GeoAppEntity> getById(ObjectId id) {
		return geoAppRepository.findById(id);
	}
	
	public void deleteById(ObjectId id) {
		geoAppRepository.deleteById(id);
	}
}


// controller ---> service --->> repository