package com.rahulsmgv.geoapp.entity;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Document(collection = "geoapp_entity")
@Data
public class GeoAppEntity {
	@Id
	private ObjectId id; 
	@NonNull
	private String title;
	private String content;
	private LocalDateTime date;
		
}