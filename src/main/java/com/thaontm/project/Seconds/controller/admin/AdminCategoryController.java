package com.thaontm.project.Seconds.controller.admin;

import com.thaontm.project.Seconds.model.Category;
import com.thaontm.project.Seconds.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RequestMapping("/admin")
@Controller
public class AdminCategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String getCategories(Map<String, Object> model) {
        model.put("categories", categoryService.findAll());
        return "/admin/categories";
    }

    @RequestMapping(value = "/category/add/", method = RequestMethod.POST)
    public String addCategory(@RequestParam String title) {
        categoryService.save(new Category(title, null));
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "/category/update/{catId}", method = RequestMethod.GET)
    public String showUpdateCategory(Map<String, Object> model, @PathVariable("catId") Integer catId) {
        model.put("category", categoryService.findOne(catId));
        return "/admin/update_category";
    }

    @RequestMapping(value = "/category/update/{catId}", method = RequestMethod.POST)
    public String updateCategory(@RequestParam Integer catId, @RequestParam String title) {
        Category category = new Category();
        category.setId(catId);
        category.setTitle(title);
        category.setProducts(categoryService.findOne(catId).getProducts());
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "/category/update/{catId}", method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable("catId") Integer catId) {
        categoryService.delete(categoryService.findOne(catId));
        return "redirect:/admin/category";
    }
}
