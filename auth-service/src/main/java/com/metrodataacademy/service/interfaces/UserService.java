package com.metrodataacademy.service.interfaces;

import com.metrodataacademy.domain.entity.User;

public interface UserService {
    User getByEmailOrUsername(String user);
    User getByUsername(String username);
}
