package com.metrodataacademy.service.impl;

import com.metrodataacademy.domain.constant.ConstantVariables;
import com.metrodataacademy.domain.dto.request.ReqUpdateUserDto;
import com.metrodataacademy.domain.dto.response.ResTemplateDto;
import com.metrodataacademy.domain.entity.User;
import com.metrodataacademy.domain.mapper.UserMapper;
import com.metrodataacademy.repository.UserRepository;
import com.metrodataacademy.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getByEmailOrUsername(String user) {
        return userRepository.findByEmailOrUsername(user, user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public ResponseEntity<ResTemplateDto> updateUser(ReqUpdateUserDto reqUpdateUserDto, String authToken) {
        try {
            Map<String, List<String>> errors = new HashMap<>();

            User userCheck = userRepository.findByEmail(reqUpdateUserDto.getEmail());
            if (userCheck != null) {
                errors.put("email", new ArrayList<>(List.of("Email has already been registered.")));
            }

            userCheck = userRepository.findByUsername(reqUpdateUserDto.getUsername());
            if (userCheck != null) {
                errors.put("username", new ArrayList<>(List.of("Username has already been registered.")));
            }

            if (!errors.isEmpty()) {
                // Return a map of field errors and a "Bad Request" status
                return new ResponseEntity<>(new ResTemplateDto(errors, "Email or username has already been registered."), HttpStatus.BAD_REQUEST);
            }

            userMapper.update(userCheck, reqUpdateUserDto);
            userRepository.save(userCheck);
            return new ResponseEntity<>(new ResTemplateDto(userCheck, ConstantVariables.SUCCESS_MESSAGE), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getReason());
        }
    }
}
