package com.rahulsmgv.geoapp.controller;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.rahulsmgv.geoapp.service.GeoAppService;

@RestController
@RequestMapping("/geo/app")
public class GeoAppController {
	
	@Autowired
	private GeoAppService geoAppService;
	
	@GetMapping("/all")
	public List<GeoAppEntity> getAll(){
		return geoAppService.getAll();
	}
	
	@PostMapping("/create")
	public GeoAppEntity createEntity(@RequestBody GeoAppEntity createEntity) {
		createEntity.setDate(LocalDateTime.now());
		geoAppService.saveEntry(createEntity);
		return createEntity;
	}
	
	@GetMapping("/id/{geoAppId}")
	public ResponseEntity<GeoAppEntity> getGeoAppEntiyById(@PathVariable ObjectId geoAppId) {
		Optional<GeoAppEntity> geoappEntity = geoAppService.getById(geoAppId);
		if(geoappEntity.isPresent()) {
			return new ResponseEntity<>(geoappEntity.get(), HttpStatus.OK);
		}
		return new ResponseEntity<GeoAppEntity>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/id/{geoAppId}")
	public boolean deleteGeoAppById(@PathVariable ObjectId geoAppId) {
		geoAppService.deleteById(geoAppId);
		return true;
	}
	
	@PutMapping("/update/{geoAppId}")
	public GeoAppEntity updateGeoAppById(@PathVariable ObjectId geoAppId, @RequestBody GeoAppEntity geoAppUpdateEntity) {
		GeoAppEntity oldData = geoAppService.getById(geoAppId).orElse(null);
		if(oldData != null) {
			oldData.setContent(geoAppUpdateEntity.getContent() != null && !geoAppUpdateEntity.getContent().equals("")? geoAppUpdateEntity.getContent():oldData.getContent());
			oldData.setTitle(geoAppUpdateEntity.getTitle() != null && !geoAppUpdateEntity.getTitle().equals("")? geoAppUpdateEntity.getTitle():oldData.getTitle());
		}
		
		geoAppService.saveEntry(oldData);
		return oldData;
	}
	
}





