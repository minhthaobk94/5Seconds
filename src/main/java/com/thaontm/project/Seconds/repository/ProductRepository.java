package com.thaontm.project.Seconds.repository;

import com.thaontm.project.Seconds.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{
}
