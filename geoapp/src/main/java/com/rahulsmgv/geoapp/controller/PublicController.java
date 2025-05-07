package com.rahulsmgv.geoapp.controller;

import com.rahulsmgv.geoapp.entity.UserEntity;
import com.rahulsmgv.geoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

//Set Profiles only run at the dev mode
@Profile("dev")
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){ return "OK";}

//    @PostMapping
//    public void createUser(@RequestBody UserEntity userEntity){
//        userService.saveNewEntry(userEntity);
//    }
}