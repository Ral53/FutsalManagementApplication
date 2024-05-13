package com.example.futsalmanagement.service.impl;

import com.example.futsalmanagement.entity.Booking;
import com.example.futsalmanagement.entity.User;
import com.example.futsalmanagement.pojo.BookingPojo;
import com.example.futsalmanagement.pojo.UserPojo;
import com.example.futsalmanagement.repository.UserRepository;
import com.example.futsalmanagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserSereviceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserSereviceImpl(UserRepository userRepository) {this.userRepository = userRepository;}


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public UserPojo createUser(UserPojo userPojo) {
        User user = new User();
        user.setEmail(userPojo.getEmail());
        user.setUsername(userPojo.getUsername());
        user.setPassword(userPojo.getPassword());
        user.setName(userPojo.getName());
        user.setPhoneNumber(userPojo.getPhoneNumber());
        User savedUser = userRepository.save(user);
        return convertToPojo(savedUser);
    }

    @Override
    public boolean updateUser(Integer id, UserPojo userPojo) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setEmail(userPojo.getEmail());
            existingUser.setUsername(userPojo.getUsername());
            existingUser.setPassword(userPojo.getPassword());
            existingUser.setName(userPojo.getName());
            existingUser.setPhoneNumber(userPojo.getPhoneNumber());
            userRepository.save(existingUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }


    // Helper methods to convert between Entity and Pojo
    private User convertToEntity(UserPojo userPojo) {
        User user = new User();
        BeanUtils.copyProperties(userPojo, user);
        return user;
    }

    private UserPojo convertToPojo(User user) {
        UserPojo userPojo = new UserPojo();
        BeanUtils.copyProperties(user, userPojo);
        return userPojo;
    }
}
