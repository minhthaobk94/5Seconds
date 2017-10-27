package com.thaontm.project.Seconds.service.impl;

import com.thaontm.project.Seconds.model.Product;
import com.thaontm.project.Seconds.repository.ProductRepository;
import com.thaontm.project.Seconds.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        return (List<Product>) productRepository.findAll(new Sort(Sort.Direction.DESC, "productName"));
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findDistinctByIdNotIn(Integer id) {
        return productRepository.findDistinctByIdNotIn(id, new Sort(Sort.Direction.DESC, "productName"));
    }

    @Override
    public List<Product> findByProductNameContaining(String q) {
        return productRepository.findByProductNameContaining(q, new Sort(Sort.Direction.DESC, "productName"));
    }

    @Override
    public Page<Product> findByProductNameContaining(String q, Pageable pageable) {
        return productRepository.findByProductNameContaining(q,new Sort(Sort.Direction.DESC, "productName"), pageable);
    }
}
