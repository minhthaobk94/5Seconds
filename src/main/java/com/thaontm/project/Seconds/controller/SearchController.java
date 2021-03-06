package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.model.Product;
import com.thaontm.project.Seconds.service.CategoryService;
import com.thaontm.project.Seconds.service.ProductService;
import com.thaontm.project.Seconds.utils.CartUtils;
import com.thaontm.project.Seconds.utils.Constants;
import com.thaontm.project.Seconds.utils.PaginationUtils;
import com.thaontm.project.Seconds.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    StringUtils utils = new StringUtils();

    @RequestMapping(value = "/search/", method = RequestMethod.GET)
    public String search(Map<String, Object> model, @RequestParam(name = "q", defaultValue = "") String q, @PageableDefault(page = 0, size = 12)Pageable pageable, HttpSession session) {
        if (!q.isEmpty()) {
            Page<Product> products =   productService.findByProductNameContaining(q, pageable);
            for (Product product : products) {
                utils.cutProductDescription(product);
            }
            model.put("totalPages", PaginationUtils.calculateTotalPages(productService.findByProductNameContaining(q).size(), Constants.PAGE_SIZE));
            model.put("products", products);
        } else {
            Page<Product> products =   productService.findAll(pageable);
            for (Product product : products) {
                utils.cutProductDescription(product);
            }
            model.put("totalPages", PaginationUtils.calculateTotalPages(productService.findAll().size(), Constants.PAGE_SIZE));
           model.put("products", products);
        }
        model.put("categories", categoryService.findAll());
        model.put("pageSize", Constants.PAGE_SIZE);
        model.put("query", q);
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", CartUtils.getInstance(session).getCartItems());
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        return "search_result";
    }
}
