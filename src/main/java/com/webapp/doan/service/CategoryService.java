package com.webapp.doan.service;

import com.webapp.doan.exceptions.EtBadRequestException;
import com.webapp.doan.exceptions.EtResourceNotFoundException;
import com.webapp.doan.model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories() throws EtResourceNotFoundException;

    public Category createCategory(Category category) throws EtBadRequestException;

    public Category updateCategoryById(Integer id, Category category) throws EtBadRequestException;
}
