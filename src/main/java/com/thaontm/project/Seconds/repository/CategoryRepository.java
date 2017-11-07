package com.thaontm.project.Seconds.repository;

import com.thaontm.project.Seconds.model.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {
    Category findTopByOrderByIdDesc();

    @Override
    Iterable<Category> findAll(Sort sort);

}
