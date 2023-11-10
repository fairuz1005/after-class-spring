package com.metrodataacademy.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReqChangePassword {
    @NotBlank
    private String newPassword;

    @NotBlank
    private String oldPassword;
}
