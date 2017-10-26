package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.service.CategoryService;
import com.thaontm.project.Seconds.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    public String showClickedProductDetail(Map<String, Object> model, @PathVariable("productId") Integer productId) {
        model.put("categories", categoryService.findAll());
        model.put("categoryId", productService.findOne(productId).getCategory().getId());
        model.put("product", productService.findOne(productId));
        model.put("relative_products", productService.findDistinctByIdNotIn(productId));
        return "product_detail";
    }
}
