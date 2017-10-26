package com.thaontm.project.Seconds.service.impl;

import com.thaontm.project.Seconds.model.Product;
import com.thaontm.project.Seconds.repository.ProductRepository;
import com.thaontm.project.Seconds.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findOne(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public List<Product> findDistinctByIdNotIn(Integer id) {
        return productRepository.findDistinctByIdNotIn(id);
    }

    @Override
    public List<Product> findByProductNameContaining(String q) {
        return productRepository.findByProductNameContaining(q);
    }
}
