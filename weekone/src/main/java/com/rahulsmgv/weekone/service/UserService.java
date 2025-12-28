package com.rahulsmgv.weekone.service;

import com.rahulsmgv.weekone.model.User;
import com.rahulsmgv.weekone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getByUserId(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails){
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setAddress(userDetails.getAddress());
            user.setRole(userDetails.getRole());
            user.setAge(userDetails.getAge());
            user.setContact(userDetails.getContact());
            return userRepository.save(user);
        }).orElse(null);
    }

    public void deleteUser(Long id){
         userRepository.deleteById(id);
    }
}
