package com.example.crudex.exception.user.controller;


import com.example.crudex.exception.user.dto.UserRequest;
import com.example.crudex.exception.user.entity.User;
import com.example.crudex.exception.user.exception.DeleteException;
import com.example.crudex.exception.user.exception.SameNameException;
import com.example.crudex.exception.user.exception.UserNotFoundException;
import com.example.crudex.exception.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRequest userRequest) throws SameNameException {
        return new ResponseEntity<>(service.saveUser(userRequest), HttpStatus.CREATED);
    }

//    @GetMapping("/users")
//    public  ResponseEntity<List<User>> getAllUsers() {
//        return ResponseEntity.ok(service.getAllUsers());
//    }

    @GetMapping("/users")
    Page<User> users(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy){
        return service.getAllUsers(page, sortBy);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(service.getUser(id));
    }



    @PutMapping("/update/{userId}")
    public User updateUser(@PathVariable("userId") Integer userId, @RequestBody User user) throws UserNotFoundException {

        return service.updateUser(userId, user);
    }

    @PatchMapping("/update/{userId}")
    public User updateUserWithMap(@PathVariable("userId") Integer userId, @RequestBody Map<Object, Object> objectMap) throws UserNotFoundException {
        return service.updateUserWithMap(userId, objectMap);
    }




    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) throws UserNotFoundException, DeleteException {
        return service.deleteUser(id);
    }




}
