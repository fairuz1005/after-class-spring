package com.metrodataacademy.domain.dto.response;

import com.metrodataacademy.domain.dto.AuthorizationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResBaseValidateTokenDto {
    private AuthorizationDto data;
    private String message;
}
