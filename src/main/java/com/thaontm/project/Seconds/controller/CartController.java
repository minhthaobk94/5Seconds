package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.model.Customer;
import com.thaontm.project.Seconds.model.OrderInfo;
import com.thaontm.project.Seconds.service.CustomerService;
import com.thaontm.project.Seconds.service.ProductService;
import com.thaontm.project.Seconds.utils.CartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCart(Map<String, Object> model, HttpSession session) {
        model.put("cart", CartUtils.getInstance(session).getCartItems());
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        return "cart";
    }

    @RequestMapping(value = "/addToCart/{productId}", method = RequestMethod.GET)
    public String addToCart(Map<String, Object> model, HttpSession session, @PathVariable("productId") Integer productId, HttpServletRequest request) {
        CartUtils.getInstance(session).addToCart(productService.findOne(productId));
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkout(Map<String, Object> model, HttpSession session) {
        CartUtils.getInstance(session).checkout();
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", session.getAttribute(CartUtils.SESSION_ATTRIBUTE_CART));
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        return "checkout";
    }

    @RequestMapping(value = "/checkout/bill", method = RequestMethod.POST)
    public String getBill(Map<String, Object> model, @RequestParam("order_note") String note, HttpSession session, @RequestParam("customer") Customer customer) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setNote(note);
        model.put("note", note);
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", session.getAttribute(CartUtils.SESSION_ATTRIBUTE_CART));
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        model.put("customer", customer);
        return "bill";
    }

    @RequestMapping(value = "/cart/remove/{productId}", method = RequestMethod.GET)
    public String removeCartItem(Map<String, Object> model, HttpSession session, @PathVariable("productId") Integer productId) {
        CartUtils.getInstance(session).removeCartItem(productService.findOne(productId));
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        return "redirect:/cart";
    }
}
