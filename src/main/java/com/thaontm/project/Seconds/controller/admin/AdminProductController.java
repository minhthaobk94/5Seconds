package com.thaontm.project.Seconds.controller.admin;

import com.thaontm.project.Seconds.model.Product;
import com.thaontm.project.Seconds.service.CategoryService;
import com.thaontm.project.Seconds.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getProducts(Map<String, Object> model) {
        model.put("products", productService.findAll());
        return "/admin/products";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddProduct(Map<String, Object> model) {
        model.put("categories", categoryService.findAll());
        return "/admin/add_product";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(@RequestParam("name") String productName, @RequestParam("description") String description, @RequestParam("url") String imageUrl, @RequestParam("price") double price, @RequestParam("category") String catTitle) {
        productService.save(new Product(categoryService.findByTitle(catTitle), productName, description, imageUrl, price, null));
        return "redirect:/admin/product/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUpdatePage(Map<String, Object> model, @PathVariable("id") Integer productId) {
        model.put("product", productService.findOne(productId));
        model.put("categories", categoryService.findAll());
        return "admin/update_product";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateProduct(@RequestParam("id") Integer id, @RequestParam("name") String productName, @RequestParam("description") String description, @RequestParam("url") String imageUrl, @RequestParam("price") double price, @RequestParam("category") String catTitle) {
        Product product = new Product();
        product.setId(id);
        product.setDescription(description);
        product.setProductName(productName);
        product.setImgUrl(imageUrl);
        product.setPrice(price);
        product.setCategory(categoryService.findByTitle(catTitle));
        productService.save(product);
        return "redirect:/admin/product/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") Integer id){
        productService.delete(productService.findOne(id));
        return "redirect:/admin/product/";
    }
}
