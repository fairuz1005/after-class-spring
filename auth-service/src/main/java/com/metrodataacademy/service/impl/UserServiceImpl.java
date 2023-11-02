package com.metrodataacademy.service.impl;

import com.metrodataacademy.domain.constant.ConstantVariables;
import com.metrodataacademy.domain.dto.request.ReqRedisUpdateProfileDto;
import com.metrodataacademy.domain.dto.request.ReqRegisterDto;
import com.metrodataacademy.domain.dto.request.ReqUpdateUserDto;
import com.metrodataacademy.domain.dto.response.ResTemplateDto;
import com.metrodataacademy.domain.dto.response.ResUserProfileDto;
import com.metrodataacademy.domain.entity.User;
import com.metrodataacademy.domain.mapper.UserMapper;
import com.metrodataacademy.repository.UserRepository;
import com.metrodataacademy.service.interfaces.UserService;
import com.metrodataacademy.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
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

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RedisTemplate<String, ReqRedisUpdateProfileDto> publishUpdateProfile;

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

            userMapper.update(userCheck, reqUpdateUserDto);
            userRepository.save(userCheck);
            ResUserProfileDto resUserProfileDto = userMapper.userToResProfileDto(userCheck);

            ReqRedisUpdateProfileDto reqRedisUpdateProfileDto = new ReqRedisUpdateProfileDto(userCheck.getId(), userCheck.getName());
            publishUpdateProfile.convertAndSend("UPDATE_PROFILE", reqRedisUpdateProfileDto);

            return new ResponseEntity<>(new ResTemplateDto(resUserProfileDto, ConstantVariables.SUCCESS_MESSAGE), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getReason());
        }
    }

    @Override
    public ResponseEntity<ResTemplateDto> getProfile(String authToken) {
        String token = authToken.substring(7, authToken.length());
        String username = jwtUtil.extractUsername(token);

        User user = userRepository.findByUsername(username);

        ResUserProfileDto resUserProfileDto = userMapper.userToResProfileDto(user);

        return new ResponseEntity<>(new ResTemplateDto(resUserProfileDto, ConstantVariables.SUCCESS_MESSAGE), HttpStatus.OK);
    }
}
