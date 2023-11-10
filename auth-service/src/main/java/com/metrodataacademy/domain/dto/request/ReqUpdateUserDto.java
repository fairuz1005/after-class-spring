package com.metrodataacademy.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ReqUpdateUserDto {

    @NotBlank
    private String name;

    @NotBlank
    private String alamat;

    @NotBlank
    private String jenisKelamin;

    private String profilePicture;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate tanggalLahir;

    @NotBlank
    @Email
    private String email;
}
