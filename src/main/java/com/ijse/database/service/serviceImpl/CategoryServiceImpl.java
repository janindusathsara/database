package com.ijse.database.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.entity.Category;
import com.ijse.database.repository.CategoryRepository;
import com.ijse.database.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public Category updateCategory(Long id, Category categoryEntity) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);

        if (existingCategory != null) {
            existingCategory.setName(categoryEntity.getName());
            return categoryRepository.save(existingCategory);
        } else{
            return null;
        }
    }
    
}
