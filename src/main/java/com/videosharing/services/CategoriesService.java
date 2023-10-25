package com.videosharing.services;

import java.util.List;

import com.videosharing.models.Categories;

public interface CategoriesService {
    Categories getCategoryById(Integer id);
    List<Categories> getAllCategories();
    Categories createCategory(Categories category);
    Categories updateCategory(Integer id, Categories category);
    void deleteCategory(Integer id);
}
