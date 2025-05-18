package com.rahulsmgv.geoapp.service;

import com.rahulsmgv.geoapp.entity.UserEntity;
import com.rahulsmgv.geoapp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

public class UserServiceImplTests {
    @InjectMocks
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    @BeforeEach
    @Disabled
    void setUP(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Disabled
    void userNameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(UserEntity.builder().userName("Manoj").password("Manoj@2025").roles(new ArrayList<>()).build());
        UserEntity userEntity = userService.findByUsername("Ram");
        Assertions.assertNotNull(userEntity);
    }
}
