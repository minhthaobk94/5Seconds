package com.thaontm.project.Seconds.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(value = "/")
    public String showAdminPage() {
        return "/admin/dashboard";
    }

    @RequestMapping("/dashboard")
    public String getDashboard() {
        return "redirect:/admin/";
    }

    @RequestMapping("/blank")
    public String getBlank() {
        return "/admin/blank";
    }
}
