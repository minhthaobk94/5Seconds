package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.dto.CartDTO;
import com.thaontm.project.Seconds.model.Product;
import com.thaontm.project.Seconds.service.ProductService;
import com.thaontm.project.Seconds.utils.CartUtils;
import com.thaontm.project.Seconds.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCart(Map<String, Object> model, HttpSession session) {
        model.put("total", session.getAttribute("total"));
        model.put("cart", session.getAttribute("cart"));
        return "cart";
    }

    @RequestMapping(value = "/addToCart/{productId}", method = RequestMethod.GET)
    public String addToCart(Map<String, Object> model, HttpSession session, @PathVariable("productId") Integer productId) {
        Product product = productService.findOne(productId);
        if (session.getAttribute("cart") == null) {
            List<CartDTO> cart = new ArrayList<>();
            cart.add(new CartDTO(productId, product.getProductName(), product.getImgUrl(), product.getPrice(), Constants.QUANTITY_DEFAULT));
            session.setAttribute("cart", cart);
        } else {
            int index = CartUtils.isExisting(productId, session);
            List<CartDTO> cart = (List<CartDTO>) session.getAttribute("cart");
            if (index == -1) {
                cart.add(new CartDTO(productId, product.getProductName(), product.getImgUrl(), product.getPrice(), Constants.QUANTITY_DEFAULT));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        session.setAttribute("total", CartUtils.calculateTotal(productId, session));
        return "redirect:/";
    }
}
