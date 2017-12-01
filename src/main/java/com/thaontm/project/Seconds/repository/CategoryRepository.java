package com.thaontm.project.Seconds.repository;

import com.thaontm.project.Seconds.model.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findTopByOrderByIdDesc();

    List<Category> findAll(Sort sort);

    Category findCategoryByTitleIsLike(String s);

}
