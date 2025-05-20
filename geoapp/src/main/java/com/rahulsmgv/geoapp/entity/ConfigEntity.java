package com.rahulsmgv.geoapp.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "geoapp_config")
@Data
public class ConfigEntity {
    private String api_key;
    private String api_value;
}
