package com.metrodataacademy.domain.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ResValidateTokenDto {
    private String id;
    private String name;
    private String email;
    private List<String> roles;
}
