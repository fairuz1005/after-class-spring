package com.metrodataacademy.service.interfaces;

import com.metrodataacademy.domain.dto.request.ReqChangePassword;
import com.metrodataacademy.domain.dto.request.ReqUpdateUserDto;
import com.metrodataacademy.domain.dto.response.ResTemplateDto;
import com.metrodataacademy.domain.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User getByEmailOrUsername(String user);
    User getByUsername(String username);

    ResponseEntity<ResTemplateDto> getProfile(String authToken);
    ResponseEntity<ResTemplateDto> updateUser(ReqUpdateUserDto reqUpdateUserDto, String authToken);

    ResponseEntity<ResTemplateDto> changePassword(ReqChangePassword reqChangePassword, String authToken);
}
