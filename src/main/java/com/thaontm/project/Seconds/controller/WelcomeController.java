package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/")
    public String getCategories(Map<String, Object> model) {
        model.put("catId", categoryService.findTopByOrderByIdDesc().getId());
        model.put("categories", categoryService.findAll());
        model.put("products", categoryService.findTopByOrderByIdDesc().getProducts());
        return "welcome";
    }
}
