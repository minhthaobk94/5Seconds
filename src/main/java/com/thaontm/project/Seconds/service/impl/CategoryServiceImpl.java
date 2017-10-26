package com.thaontm.project.Seconds.service.impl;

import com.thaontm.project.Seconds.model.Category;
import com.thaontm.project.Seconds.repository.CategoryRepository;
import com.thaontm.project.Seconds.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findTopByOrderByIdDesc() {
        return categoryRepository.findTopByOrderByIdDesc();
    }

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category findOne(int catId) {
        return categoryRepository.findOne(catId);
    }
}
