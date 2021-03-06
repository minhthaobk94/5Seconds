package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.service.CategoryService;
import com.thaontm.project.Seconds.service.CustomerService;
import com.thaontm.project.Seconds.service.ProductService;
import com.thaontm.project.Seconds.utils.CartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCart(Map<String, Object> model, HttpSession session) {
        model.put("cart", CartUtils.getInstance(session).getCartItems());
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("categories", categoryService.findAll());
        return "cart";
    }

    @RequestMapping(value = "/addToCart/{productId}", method = RequestMethod.GET)
    public String addToCart(Map<String, Object> model, HttpSession session, @PathVariable("productId") Integer productId, HttpServletRequest request) {
        CartUtils.getInstance(session).addToCart(productService.findOne(productId));
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

    @RequestMapping(value = "/cart/remove/{productId}", method = RequestMethod.GET)
    public String removeCartItem(Map<String, Object> model, HttpSession session, @PathVariable("productId") Integer productId) {
        CartUtils.getInstance(session).removeCartItem(productService.findOne(productId));
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", CartUtils.getInstance(session).getCartItems());
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        return "redirect:/cart";
    }
}
