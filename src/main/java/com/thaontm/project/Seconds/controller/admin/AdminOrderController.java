package com.thaontm.project.Seconds.controller.admin;

import com.thaontm.project.Seconds.service.OrderInfoService;
import com.thaontm.project.Seconds.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    OrderItemService orderItemService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getOrders(Map<String, Object> model) {
        model.put("orders", orderInfoService.findAll());
        return "/admin/orders";
    }

    @RequestMapping(value = "/detail/{orderId}", method = RequestMethod.GET)
    public String getOrderDetail(Map<String, Object> model, @PathVariable("orderId") Integer orderId) {
        model.put("order", orderInfoService.findOne(orderId));
        model.put("orderItems", orderItemService.findAllByOrderInfo(orderInfoService.findOne(orderId)));
        return "/admin/order_detail";
    }
}
