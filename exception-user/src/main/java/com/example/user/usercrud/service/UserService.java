package com.example.user.usercrud.service;

import com.example.user.usercrud.dto.UserRequest;
import com.example.user.usercrud.entity.User;
import com.example.user.usercrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(UserRequest userRequest) {
        User user = User.build(0, userRequest.getFirstName(), userRequest.getLastName(), userRequest.getAge());
        return repository.save(user);
    }


    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(int id){
        return repository.findByUserId(id);
    }
}
