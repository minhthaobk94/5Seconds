package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String getSearchOrderPage() {
        return "search_order";
    }

    @RequestMapping(value = "/search/", method = RequestMethod.POST)
    public String searchOrder(Map<String, Object> model, @RequestParam("orderId") Integer orderId) {
        model.put("orderInfo", orderInfoService.findOne(orderId));
        return "order_history";
    }
}
