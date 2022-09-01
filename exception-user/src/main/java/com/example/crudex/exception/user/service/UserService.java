package com.example.crudex.exception.user.service;


import com.example.crudex.exception.user.dto.UserRequest;
import com.example.crudex.exception.user.entity.User;
import com.example.crudex.exception.user.exception.DeleteException;
import com.example.crudex.exception.user.exception.SameNameException;
import com.example.crudex.exception.user.exception.UserNotFoundException;
import com.example.crudex.exception.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


//    public User saveUser(UserRequest userRequest) {
//        User user = User.build(0, userRequest.getFirstName(), userRequest.getLastName(), userRequest.getAge());
//        return repository.save(user);
//    }

    public User saveUser(UserRequest userRequest) throws SameNameException {
        if(userRequest.getFirstName().equals(userRequest.getLastName()) == false) {
            User user = User.build(0, userRequest.getFirstName(), userRequest.getLastName(), userRequest.getAge());
            return repository.save(user);

        }
        else {
//            User user = User.build(0, userRequest.getFirstName(), userRequest.getLastName(), userRequest.getAge());
//            return repository.save(user);
            throw new SameNameException("First name and last name cannot be same");
        }

    }


    public Page<User> getAllUsers(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy) {
        return repository.findAll(PageRequest.of(page.orElse(0), 5, Sort.Direction.ASC, sortBy.orElse("userId")));
    }

    public User getUser(int id) throws UserNotFoundException {
        User user = repository.findByUserId(id);
        if(user!= null) {
            return user;
        }

        else {
            throw  new UserNotFoundException("There is no user with this id");
        }
//        return repository.findByUserId(id);
    }


//    public String deleteUser(int id) {
//        repository.deleteById(id);
//        return "Removed";
//    }


    public String deleteUser(int id) throws DeleteException, UserNotFoundException {
        User user = repository.getReferenceById(id);
        if(user!=null) {
            repository.deleteById(id);
            throw new DeleteException("User is deleted");
        }
        else {
            throw new UserNotFoundException("There is no user with this Id");
        }
//        repository.deleteById(id);
//        return "Removed";
    }

    public User updateUserWithMap(Integer userId, Map<Object, Object> objectMap) throws UserNotFoundException {
        User user = repository.getReferenceById(userId);
        if(user!=null) {
            objectMap.forEach((key, value) -> {
                if ("firstName".equals(key)) {
                    user.setFirstName((String) value);
                } else if ("lastName".equals(key)) {
                    user.setLastName((String) value);
                } else if ("age".equals(key)) {
                    user.setAge((Integer) value);
                }
            });

            return repository.save(user);
        }
        else {
            throw  new UserNotFoundException("There is no user with this id");
        }
//        objectMap.forEach((key, value) -> {
//            if ("firstName".equals(key)) {
//                user.setFirstName((String) value);
//            } else if ("lastName".equals(key)) {
//                user.setLastName((String) value);
//            } else if ("age".equals(key)) {
//                user.setAge((Integer) value);
//            }
//        });
//
//        return repository.save(user);
    }


    public User updateUser(Integer userId, User user) throws UserNotFoundException {
        User existingUser = repository.findByUserId(userId);
        if(existingUser!= null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setAge(user.getAge());
            return repository.save(existingUser);
        }

        else {
            throw  new UserNotFoundException("There is no user with this id");
        }
//        User existingUser = repository.getReferenceById(userId);
//        existingUser.setFirstName(user.getFirstName());
//        existingUser.setLastName(user.getLastName());
//        existingUser.setAge(user.getAge());
//        return repository.save(existingUser);
    }
}
