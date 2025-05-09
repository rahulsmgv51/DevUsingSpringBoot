package com.rahulsmgv.geoapp.controller;

import com.rahulsmgv.geoapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

//@Slf4j
//Set Profiles only run at the dev mode
@Profile("dev")
@RestController
@RequestMapping("/public")
public class PublicController {

//    private static final Logger logger = (Logger) LoggerFactory.getLogger(PublicController.class);

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        try {
//            logger.info("Hello I am checking logging module with logger ");
            return "OK";
        }catch (Exception ignored){
//            log.info("Exception in the class of {}", ignored.getClass(), ignored.getMessage());
//            log.debug("Exception in the class of {}", ignored.getClass(), ignored.getMessage());
//            log.trace("Exception in the class of {}", ignored.getClass(), ignored.getMessage());
//            log.warn("Exception in the class of {}", ignored.getClass(), ignored.getMessage());
//            log.error("Exception in the class of {}", ignored.getClass(), ignored.getMessage());
            return "NoHealth";
        }
    }

//    @PostMapping
//    public void createUser(@RequestBody UserEntity userEntity){
//        userService.saveNewEntry(userEntity);
//    }
}