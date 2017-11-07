package com.thaontm.project.Seconds.service;

import com.thaontm.project.Seconds.model.Category;

import java.util.List;

public interface CategoryService {
    Category findTopByOrderByIdDesc();

    List<Category> findAll();

    Category findOne(int catId);

    void save(Category category);
}
