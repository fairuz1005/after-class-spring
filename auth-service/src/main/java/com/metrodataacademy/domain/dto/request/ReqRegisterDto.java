package com.metrodataacademy.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ReqRegisterDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    private String alamat;

    @NotBlank
    private String jenisKelamin;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate tanggalLahir;

    @NotBlank
    @Size(min = 8)
    private String password;
}
