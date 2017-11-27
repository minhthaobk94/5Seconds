package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.model.Product;
import com.thaontm.project.Seconds.service.CategoryService;
import com.thaontm.project.Seconds.service.ProductService;
import com.thaontm.project.Seconds.utils.CartUtils;
import com.thaontm.project.Seconds.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    StringUtils utils = new StringUtils();

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    public String showClickedProductDetail(Map<String, Object> model, @PathVariable("productId") Integer productId, HttpSession session) {
        List<Product> products =  productService.findDistinctByIdNotIn(productId);
        for (Product product : products) {
            utils.cutProductDescription(product);
        }
        model.put("categories", categoryService.findAll());
        model.put("categoryId", productService.findOne(productId).getCategory().getId());
        model.put("product", productService.findOne(productId));
        model.put("relative_products", products);
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", CartUtils.getInstance(session).getCartItems());
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        return "product_detail";
    }
}
