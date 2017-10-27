package com.thaontm.project.Seconds.service;

import com.thaontm.project.Seconds.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product findOne(Integer id);

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    List<Product> findDistinctByIdNotIn(Integer id);

    List<Product> findByProductNameContaining(String q);

    Page<Product> findByProductNameContaining(String q, Pageable pageable);
}
