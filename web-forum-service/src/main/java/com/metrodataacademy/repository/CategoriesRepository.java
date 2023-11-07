package com.metrodataacademy.repository;

import com.metrodataacademy.domain.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, String> {
    Optional<Categories> findByName(String name);
}
