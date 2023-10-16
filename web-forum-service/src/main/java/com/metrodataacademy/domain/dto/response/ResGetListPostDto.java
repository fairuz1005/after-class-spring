package com.metrodataacademy.domain.dto.response;

import lombok.Data;

@Data
public class ResGetListPostDto {
    private String threadsId;
    private String postId;
    private String content;
    private ResStagingUserDto author;
}
