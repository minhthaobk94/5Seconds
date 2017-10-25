package com.thaontm.project.Seconds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String getCategories(Map<String, Object> model) {
        return "welcome";
    }
}
