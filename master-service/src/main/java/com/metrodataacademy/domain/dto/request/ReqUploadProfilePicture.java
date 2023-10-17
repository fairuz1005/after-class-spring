package com.metrodataacademy.domain.dto.request;

import com.metrodataacademy.domain.dto.customvalidator.ValidImageContentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReqUploadProfilePicture {
    @NotNull
    @ValidImageContentType
    private MultipartFile image;
}
