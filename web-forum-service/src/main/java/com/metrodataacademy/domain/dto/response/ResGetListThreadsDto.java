package com.metrodataacademy.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResGetListThreadsDto {
    private String id;
    private String title;
    private String content;
    private ResStagingUserDto author;
    private Timestamp createdAt;
    private Integer totalViews;
    private long totalPostComments;
}
