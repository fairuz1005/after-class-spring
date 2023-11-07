package com.metrodataacademy.service.intrf;


import com.metrodataacademy.domain.dto.response.ResBaseDto;
import com.metrodataacademy.domain.entity.Categories;
import org.springframework.http.ResponseEntity;

public interface CategoriesService {
    Categories getCategoriesById(String id);

    ResponseEntity<ResBaseDto> getAllCategories();
}
