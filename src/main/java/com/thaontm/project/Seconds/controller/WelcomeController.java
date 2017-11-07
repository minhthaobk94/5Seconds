package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.service.CategoryService;
import com.thaontm.project.Seconds.utils.CartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class WelcomeController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/")
    public String getCategories(Map<String, Object> model, HttpSession session) {
        model.put("catId", categoryService.findTopByOrderByIdDesc().getId());
        model.put("categories", categoryService.findAll());
        model.put("products", categoryService.findTopByOrderByIdDesc().getProducts());
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        return "welcome";
    }

    @RequestMapping(value = "/products/category/{catId}", method = RequestMethod.GET)
    public String getProducts(Map<String, Object> model, @PathVariable("catId") Integer catId, HttpSession session) {
        model.put("catId", catId);
        model.put("categories", categoryService.findAll());
        model.put("products", categoryService.findOne(catId).getProducts());
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        return "welcome";
    }
}
