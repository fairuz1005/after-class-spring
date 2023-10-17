package com.metrodataacademy.service.impl;

import com.google.cloud.WriteChannel;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.metrodataacademy.domain.constant.ConstantVariable;
import com.metrodataacademy.domain.dto.AuthorizationDto;
import com.metrodataacademy.domain.dto.response.ResBaseDto;
import com.metrodataacademy.service.intrf.UploadToCdnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.UUID;

@Service
public class UploadToCdnServiceImpl implements UploadToCdnService {
    @Override
    public ResponseEntity<ResBaseDto> uploadProfilePicture(MultipartFile image, AuthorizationDto authDto) throws IOException {
        String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
        String fileName = String.format("images/profile-picture/%s-%s.%s", authDto.getEmail(), UUID.randomUUID(), extension);

        if (authDto.getProfilePicture() != null) deleteFileInCloudStorage(authDto.getProfilePicture().replace("https://storage.googleapis.com/after-class-spring/", "").replace("%20", " "));

        uploadToCloudStorage(image, fileName);
        String fileUrl = String.format("https://storage.googleapis.com/%s/%s", "after-class-spring", fileName.replace(" ", "%20"));
        return new ResponseEntity<>(new ResBaseDto(fileUrl, ConstantVariable.SUCCESS), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResBaseDto> uploadArticleImage(MultipartFile image) throws IOException {
        String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
        String fileName = String.format("images/article-image/%s.%s", UUID.randomUUID(), extension);

        uploadToCloudStorage(image, fileName);
        String fileUrl = String.format("https://storage.googleapis.com/%s/%s", "after-class-spring", fileName.replace(" ", "%20"));
        return new ResponseEntity<>(new ResBaseDto(fileUrl, ConstantVariable.SUCCESS), HttpStatus.OK);
    }

    private void uploadToCloudStorage(MultipartFile file, String fileName) throws IOException {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of("after-class-spring", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();

        try (WriteChannel writer = storage.writer(blobInfo)) {
            try (InputStream inputStream = file.getInputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    writer.write(ByteBuffer.wrap(buffer, 0, bytesRead));
                }
            }
        }
    }

    private void deleteFileInCloudStorage(String fileName) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of("besteventsolution", fileName);
        if (storage.get(blobId) != null) {
            System.out.println("delete: "+storage.delete(blobId));
        }
    }
}
