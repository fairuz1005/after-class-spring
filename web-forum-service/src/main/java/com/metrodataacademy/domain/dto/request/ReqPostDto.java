package com.metrodataacademy.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO contains list of Posts attributes
 * to help get idThreads and content for Posts list in (ReqPostsDTO)
 */
@Data
public class ReqPostDto {
    @NotBlank
    private String idThreads;

    @NotBlank
    private String content;
}
