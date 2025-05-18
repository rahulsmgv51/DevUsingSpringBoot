package com.rahulsmgv.geoapp.service;

import com.rahulsmgv.geoapp.entity.UserEntity;
import com.rahulsmgv.geoapp.repository.UserRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @Disabled
    @Test
    public void testFindByUserName(){
        //assertEquals(4, 2+2);
        //assertTrue(5>6);
        UserEntity userEntity = userRepository.findByUserName("Manoj");
        assertFalse(userEntity.getGeoappEntities().isEmpty());
        assertNotNull(userRepository.findByUserName("Manoj"));
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = { //ints, longs
            "Mohit",
            "Rohit",
            "Manoj",
            "Amit"
    })

    public void testFindByUserNameParameter(String username){
        assertNotNull(userRepository.findByUserName(username), "Test Case is failed for username: "+ username);
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testFindByUserNameArgs(UserEntity userEntity){
        assertTrue(userService.saveNewEntry(userEntity));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "4, 5, 9",
            "5, 7, 12"
    })
    public void testVariables(int a, int b, int expectedResult){
        assertEquals(expectedResult, a+b);
    }

}
