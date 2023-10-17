package com.metrodataacademy.domain.dto.customvalidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

class ImageContentTypeValidator implements ConstraintValidator<ValidImageContentType, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        String contentType = value.getContentType();

        // List of recognized video content types
        List<String> validContentTypes = Arrays.asList("image/jpeg", "image/png", "image/svg+xml");

        if (!validContentTypes.contains(contentType)) {
            return false;
        }
        return true;
    }
}
