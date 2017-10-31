package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.dto.CartDTO;
import com.thaontm.project.Seconds.service.ProductService;
import com.thaontm.project.Seconds.utils.CartUtils;
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
    public String showCart(Map<String, Object> model, HttpSession session){
        return "cart";
    }

    @RequestMapping(value = "/addToCart/{productId}", method = RequestMethod.GET)
    public String addToCart (Map<String, Object> model, HttpSession session, @PathVariable("productId") Integer productId) {
        if (session.getAttribute("cart") == null) {
            List<CartDTO> cart = new ArrayList<>();
            cart.add(new CartDTO(productId));
            session.setAttribute("cart", cart);
        } else {
            int index = CartUtils.isExisting(productId, session);
            List<CartDTO> cart = (List<CartDTO>) session.getAttribute("cart");
            if (CartUtils.isExisting(productId, session) == -1) {
                cart.add(new CartDTO(productId));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("index", index);
            session.setAttribute("cart", cart);
        }
        return "redirect:/";
    }
}
