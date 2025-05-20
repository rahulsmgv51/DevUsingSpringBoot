package com.rahulsmgv.geoapp.controller;

import com.rahulsmgv.geoapp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geo/app")
public class UtilityController {
    @Autowired
    AppCache appCache;

    @GetMapping("/clear/cache")
    public boolean reInitiateCache(){
        try{
            appCache.init();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}