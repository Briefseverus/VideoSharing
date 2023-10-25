package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.Categories;
import com.videosharing.repositories.CategoriesRepository;
import com.videosharing.services.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {
	@Autowired
	private CategoriesRepository categoriesRepository;

	@Override
	public Categories getCategoryById(Integer id) {
		return categoriesRepository.findById(id).orElse(null);
	}

	@Override
	public List<Categories> getAllCategories() {
		return categoriesRepository.findAll();
	}

	@Override
	public Categories createCategory(Categories category) {
		return categoriesRepository.save(category);
	}

	@Override
	public Categories updateCategory(Integer id, Categories category) {
		Categories existingCategory = categoriesRepository.findById(id).orElse(null);
		if (existingCategory != null) {
			existingCategory.setName(category.getName());
			// ...
			return categoriesRepository.save(existingCategory);
		}
		return null;
	}

	@Override
	public void deleteCategory(Integer id) {
		categoriesRepository.deleteById(id);
	}
}
