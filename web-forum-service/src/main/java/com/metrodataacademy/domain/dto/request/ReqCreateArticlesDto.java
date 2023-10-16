package com.metrodataacademy.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * DTO to insert title, body, banner, image data from tables (tb_m_articles)
 * and categories from the table (tb_m_categories)
 */
@Data
public class ReqCreateArticlesDto {
    @NotBlank
    private String title;

    @NotBlank
    private String body;

    @NotBlank
    private String banner;

    @NotBlank
    private String imageUrl;


    private List<ReqListCategoryDto> categories;

}
