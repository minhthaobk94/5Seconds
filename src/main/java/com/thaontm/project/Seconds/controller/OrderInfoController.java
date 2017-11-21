package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.model.OrderInfo;
import com.thaontm.project.Seconds.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/note", method = RequestMethod.POST)
    public String addNote(Map<String, Object> model, @RequestParam("order_note") String note){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setNote(note);
        model.put("note", note);
        return "redirect:/order/note";
    }
}
