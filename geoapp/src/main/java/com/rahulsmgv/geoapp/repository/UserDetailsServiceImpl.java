//package com.rahulsmgv.geoapp.repository;
//
//import com.rahulsmgv.geoapp.entity.UserEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity byUserName = userRepository.findByUserName(username);
//        if (byUserName != null){
//            UserDetails userDetails = User.builder()
//                    .username(byUserName.getUserName())
//                    .password(byUserName.getPassword())
//                    .roles(byUserName.getRoles().toArray(new String[0]))
//                    .build();
//            return userDetails;
//        }
//        throw new UsernameNotFoundException("User not found with username: "+ username);
//    }
//}
