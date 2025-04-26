//package com.rahulsmgv.geoapp.controller;
//
//import java.util.List;
//import java.util.Map;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.rahulsmgv.geoapp.entity.GeoAppEntity;
//
//@RestController
//@RequestMapping("/geo/app/inmem")
//public class GeoAppControllerInMem {
//	private Map<Long, GeoAppEntity> gaEntity = new HashMap<>();
//
//	@GetMapping("/all")
//	public List<GeoAppEntity> getAll() {
//		return new ArrayList<>(gaEntity.values());
//	}
//
//	@PostMapping("/create")
//	public boolean createEntity(@RequestBody GeoAppEntity createEntity) {
//		gaEntity.put(createEntity.getId(), createEntity);
//		return true;
//	}
//
//	@GetMapping("/id/{geoAppId}")
//	public GeoAppEntity getGeoAppEntiyById(@PathVariable Long geoAppId) {
//		System.out.println(" Get By ID : " + geoAppId + " Value " + gaEntity.get(geoAppId));
//		return gaEntity.get(geoAppId);
//	}
//
//	@DeleteMapping("/id/{geoAppId}")
//	public GeoAppEntity deleteGeoAppById(@PathVariable Long geoAppId) {
//		return gaEntity.remove(geoAppId);
//	}
//
//	@PutMapping("/update/{geoAppId}")
//	public GeoAppEntity updateGeoAppById(@PathVariable Long geoAppId, @RequestBody GeoAppEntity geoAppUpdateEntity) {
//		System.out.println(" Update By ID : " + geoAppId);
//		gaEntity.put(geoAppId, geoAppUpdateEntity);
//		return gaEntity.get(geoAppId);
//	}
//
//}
