package com.example.user.usercrud.repository;

import com.example.user.usercrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer > {

    User findByUserId(int id);
}
