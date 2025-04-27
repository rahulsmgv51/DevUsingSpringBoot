package com.rahulsmgv.geoapp.entity;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
	
}