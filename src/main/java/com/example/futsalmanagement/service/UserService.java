package com.example.futsalmanagement.service;

import com.example.futsalmanagement.entity.User;
import com.example.futsalmanagement.pojo.UserPojo;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Integer id);

    UserPojo createUser(UserPojo userPojo);

    boolean updateUser(Integer id, UserPojo userPojo);

    boolean deleteUserById(Integer id);
}
