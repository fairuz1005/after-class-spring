package com.metrodataacademy.service.impl;

import com.metrodataacademy.domain.dto.request.ReqRedisUpdateProfileDto;
import com.metrodataacademy.domain.entity.StagingUser;
import com.metrodataacademy.repository.StagingUserRepository;
import com.metrodataacademy.service.intrf.StagingUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StagingUserServiceImpl implements StagingUserService {

    private final StagingUserRepository stagingUserRepository;

    @Override
    public void updateProfile(ReqRedisUpdateProfileDto reqRedisUpdateProfileDto) {
        StagingUser stagingUser = stagingUserRepository.findById(reqRedisUpdateProfileDto.getId()).get();

        stagingUser.setName(reqRedisUpdateProfileDto.getName());

        stagingUserRepository.save(stagingUser);
    }
}
