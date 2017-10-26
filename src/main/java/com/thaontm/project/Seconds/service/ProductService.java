package com.thaontm.project.Seconds.service;

import com.thaontm.project.Seconds.model.Product;

import java.util.List;

public interface ProductService {
    Product findOne(Integer id);

    List<Product> findAll();

    List<Product> findDistinctByIdNotIn(Integer id);

    List<Product> findByProductNameContaining(String q);
}
