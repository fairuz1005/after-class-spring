package com.metrodataacademy.redis;

import com.metrodataacademy.domain.dto.request.ReqRedisUpdateProfileDto;
import com.metrodataacademy.domain.entity.StagingUser;
import com.metrodataacademy.service.intrf.StagingUserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Receiver {

    private final StagingUserService stagingUserService;

    public void handleMessage(ReqRedisUpdateProfileDto reqRedisUpdateProfileDto){
        stagingUserService.updateProfile(reqRedisUpdateProfileDto);
    }

//    public void handleMessage(Req..){
//
//    }
}
