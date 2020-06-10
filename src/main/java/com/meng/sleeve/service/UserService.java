package com.meng.sleeve.service;

import com.meng.sleeve.model.User;
import com.meng.sleeve.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findFirstById(id);
    }
}
