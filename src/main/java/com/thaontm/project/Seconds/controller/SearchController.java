package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/search/", method = RequestMethod.GET)
    public String search(Map<String, Object> model, @RequestParam(name = "q", defaultValue = "") String q) {
        if (!q.isEmpty()) {
            model.put("products", productService.findByProductNameContaining(q));
        } else {
           model.put("products", productService.findAll());
        }
        model.put("query", q);
        return "search_result";
    }
}
