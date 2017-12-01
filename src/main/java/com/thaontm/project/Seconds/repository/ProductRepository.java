package com.thaontm.project.Seconds.repository;

import com.thaontm.project.Seconds.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    List<Product> findDistinctByIdNotIn(Integer id, Sort sort);

    List<Product> findByProductNameContaining(String q, Sort sort);

    Page<Product> findByProductNameContaining(String q, Pageable pageable);

    @Override
    Page<Product> findAll(Pageable pageable);

    @Override
    Iterable<Product> findAll(Sort sort);

    @Override
    void delete(Product product);
}
