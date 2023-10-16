package com.metrodataacademy.domain.dto.request;

import lombok.Data;

/**
 * DTO contains list of Posts attributes
 * to help get idThreads and content for Posts list in (ReqPostsDTO)
 */
@Data
public class ReqPostDto {

    private String idThreads;
    private String content;
}
