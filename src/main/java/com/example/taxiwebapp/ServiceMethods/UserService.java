package com.example.taxiwebapp.ServiceMethods;

import com.example.taxiwebapp.EntityClasses.UsersEntity;
import com.example.taxiwebapp.EntityRepositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepository userRepository;

    public UserService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}

