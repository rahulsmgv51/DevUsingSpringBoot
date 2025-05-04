package com.rahulsmgv.geoapp.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;

@Data
@NoArgsConstructor
@Document(collection = "users_details")
public class UserEntity {
	
	@Id
	private ObjectId id;
	@Indexed(unique = true)
	@NonNull
	private String userName;
	@NonNull
	private String password;
	@DBRef
	private List<GeoAppEntity> geoappEntities = new ArrayList<>();
	private List<String> roles;
	
}