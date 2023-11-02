package com.metrodataacademy.service.intrf;

import com.metrodataacademy.domain.dto.request.ReqRedisUpdateProfileDto;

public interface StagingUserService {
    void updateProfile(ReqRedisUpdateProfileDto reqRedisUpdateProfileDto);
}
