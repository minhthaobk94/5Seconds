package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.service.CategoryService;
import com.thaontm.project.Seconds.service.OrderInfoService;
import com.thaontm.project.Seconds.utils.CartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String getSearchOrderPage(Map<String, Object> model, HttpSession session) {
        model.put("categories", categoryService.findAll());
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", CartUtils.getInstance(session).getCartItems());
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        return "order_history";
    }

    @RequestMapping(value = "/search/", method = RequestMethod.GET)
    public String searchOrder(Map<String, Object> model, @RequestParam("orderId") String orderIdSt, HttpSession session) {
        model.put("categories", categoryService.findAll());
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", CartUtils.getInstance(session).getCartItems());
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());

        try {
            model.put("orderInfo", orderInfoService.findOne(Integer.parseInt(orderIdSt)));
            model.put("total", orderInfoService.findOne(Integer.parseInt(orderIdSt)).getTotal());
        } catch (Exception e) {
            model.put("orderInfo", null);
        }
        return "order_history";
    }
}
