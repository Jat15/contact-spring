package com.animals.contact.service;


import com.animals.contact.entity.User;
import com.animals.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        boolean userExist = userRepository.existsUserByEmail(user.getEmail());

        if (userExist) {
            return null;
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User findUser(String email){
        User user = userRepository.findByEmail(email);

        return user;
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
