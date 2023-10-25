package com.videosharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    
}
