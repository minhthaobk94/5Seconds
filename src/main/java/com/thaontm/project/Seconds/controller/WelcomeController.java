package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.model.Product;
import com.thaontm.project.Seconds.service.CategoryService;
import com.thaontm.project.Seconds.utils.CartUtils;
import com.thaontm.project.Seconds.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

@Controller
public class WelcomeController {
    @Autowired
    private CategoryService categoryService;

    StringUtils utils = new StringUtils();

    @RequestMapping(value = "/")
    public String getCategories(Map<String, Object> model, HttpSession session) {
        Set<Product> products =  categoryService.findTopByOrderByIdDesc().getProducts();
        for (Product product : products) {
            utils.cutProductDescription(product);
        }
        model.put("catId", categoryService.findTopByOrderByIdDesc().getId());
        model.put("categories", categoryService.findAll());
        model.put("products", products);
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", CartUtils.getInstance(session).getCartItems());
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        return "welcome";
    }

    @RequestMapping(value = "/products/category/{catId}", method = RequestMethod.GET)
    public String getProducts(Map<String, Object> model, @PathVariable("catId") Integer catId, HttpSession session) {
        Set<Product> products =  categoryService.findOne(catId).getProducts();
        for (Product product : products) {
            utils.cutProductDescription(product);
        }
        model.put("catId", catId);
        model.put("categories", categoryService.findAll());
        model.put("products", products);
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", CartUtils.getInstance(session).getCartItems());
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        return "welcome";
    }
}
