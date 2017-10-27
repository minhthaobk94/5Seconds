package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.service.ProductService;
import com.thaontm.project.Seconds.utils.Constants;
import com.thaontm.project.Seconds.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String search(Map<String, Object> model, @RequestParam(name = "q", defaultValue = "") String q, @PageableDefault(page = 0, size = 12)Pageable pageable) {
        if (!q.isEmpty()) {
            model.put("totalPages", PaginationUtils.calculateTotalPages(productService.findByProductNameContaining(q).size(), Constants.PAGE_SIZE));
            model.put("products", productService.findByProductNameContaining(q, pageable));
        } else {
            model.put("totalPages", PaginationUtils.calculateTotalPages(productService.findAll().size(), Constants.PAGE_SIZE));
           model.put("products", productService.findAll(pageable));
        }
        model.put("pageSize", Constants.PAGE_SIZE);
        model.put("query", q);
        return "search_result";
    }
}
