package com.metrodataacademy.domain.dto.request;

import lombok.Data;


/**
 * DTO contains list of categories attributes
 * to help get id for categories list in (ReqListArticlesDTO)
 */
@Data
public class ReqListCategoryDto {
    private String id;
    private String name;
}
