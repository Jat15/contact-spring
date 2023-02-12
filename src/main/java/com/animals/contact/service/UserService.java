package com.animals.contact.service;


import com.animals.contact.entity.User;
import com.animals.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FakerService fakerService;

    public User addUser(User user) {
        boolean userExist = userRepository.existsUserByEmail(user.getEmail());

        if (userExist) {
            return null;
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        User newUser = userRepository.save(user);
        fakerService.createAllContact(20, newUser);

        return newUser;
    }

    public Optional<User> findUser(String email){
        return userRepository.findByEmail(email);
    }

    public User updateUser(User user) {

        boolean userExist = userRepository.existsUserByEmail(user.getEmail());

        if (!userExist) {
            return null;
        }

        if (!user.getPassword().contains("$2a$10$")) {
            BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
            user.setPassword(encode.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }
}
