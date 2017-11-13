package com.thaontm.project.Seconds.controller.admin;

import com.thaontm.project.Seconds.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
    @Autowired
    OrderInfoService orderInfoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getOrders(Map<String, Object> model) {
        model.put("orders", orderInfoService.findAll());
        return "/admin/orders";
    }
}
