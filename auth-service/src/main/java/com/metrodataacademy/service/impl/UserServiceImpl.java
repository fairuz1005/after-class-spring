package com.metrodataacademy.service.impl;

import com.metrodataacademy.domain.entity.User;
import com.metrodataacademy.repository.UserRepository;
import com.metrodataacademy.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;


    @Override
    public User getByEmailOrUsername(String user) {
        return userRepository.findByEmailOrUsername(user, user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
