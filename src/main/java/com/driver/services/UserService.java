package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password) {
        User user = new User();
        String arr[] = username.split("\\s");

        user.setUserName(username);
        user.setFirstName(arr[0]);
        user.setLastName(arr[1]);
        user.setPassword(password);

        userRepository3.save(user);
        return user;
    }

    public void deleteUser(int userId) {
        User user;
        try{
            user = userRepository3.findById(userId).get();
        }catch(Exception e){
            return;
        }
        userRepository3.delete(user);
    }

    public User updateUser(Integer id, String password){
        User user;
        try{
            user =  userRepository3.findById(id).get();
        }catch(Exception e){
            return null;
        }
        user.setPassword(password);
        userRepository3.save(user);
        return user;
    }
}
