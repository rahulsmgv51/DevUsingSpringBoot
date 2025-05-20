package com.rahulsmgv.geoapp.cache;

import com.rahulsmgv.geoapp.entity.ConfigEntity;
import com.rahulsmgv.geoapp.repository.ConfigGeoAppRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AppCache {
    public enum keys{
        weatherapi,
        systeminfo;
    }

    public Map<String, String> appCache;

    @Autowired
    ConfigGeoAppRepository configGeoAppRepository;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigEntity> configEntities = configGeoAppRepository.findAll();
        log.info(" Reload Cache Data {}", configEntities);
        for(ConfigEntity configEntity : configEntities){
            appCache.put(configEntity.getApi_key(), configEntity.getApi_value());
        }
    }
}
