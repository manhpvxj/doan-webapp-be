package com.webapp.doan.service;

import com.webapp.doan.exceptions.EtBadRequestException;
import com.webapp.doan.exceptions.EtResourceNotFoundException;
import com.webapp.doan.model.Category;
import com.webapp.doan.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() throws EtResourceNotFoundException {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) throws EtBadRequestException {
        return null;
    }

    @Override
    public Category updateCategoryById(Integer id, Category category) throws EtBadRequestException {
        return null;
    }
}
