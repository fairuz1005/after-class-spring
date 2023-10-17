package com.metrodataacademy.controller;

import com.metrodataacademy.domain.constant.ConstantVariable;
import com.metrodataacademy.domain.dto.AuthorizationDto;
import com.metrodataacademy.domain.dto.request.ReqUploadArticleImage;
import com.metrodataacademy.domain.dto.request.ReqUploadProfilePicture;
import com.metrodataacademy.domain.dto.response.ResBaseDto;
import com.metrodataacademy.service.impl.UploadToCdnServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/upload/image")
@RequiredArgsConstructor
public class UploadImageController {

    private final UploadToCdnServiceImpl uploadToCdnService;

    @PostMapping("/profile-picture")
    public ResponseEntity<ResBaseDto> uploadProfilePicture(@Valid @ModelAttribute ReqUploadProfilePicture reqUploadProfilePicture, HttpServletRequest servletRequest) throws IOException {
        AuthorizationDto authDto = (AuthorizationDto) servletRequest.getAttribute(ConstantVariable.USER);
        return uploadToCdnService.uploadProfilePicture(reqUploadProfilePicture.getImage(), authDto);
    }
    @PostMapping("/articles-image")
    public ResponseEntity<ResBaseDto> uploadArticleImage(@Valid @ModelAttribute ReqUploadArticleImage reqUploadArticleImage, HttpServletRequest request) throws IOException {
        return uploadToCdnService.uploadArticleImage(reqUploadArticleImage.getImage());
    }

}
