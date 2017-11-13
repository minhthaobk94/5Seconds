package com.thaontm.project.Seconds.controller.admin;

import com.thaontm.project.Seconds.model.Customer;
import com.thaontm.project.Seconds.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/admin/customer")
public class AdminCustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCustomers(Map<String, Object> model) {
        model.put("customers", customerService.findAll());
        return "/admin/customers";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddCustomer() {
        return "/admin/add_customer";
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public String addCustomer(@RequestParam String name, @RequestParam String email, @RequestParam String phone, @RequestParam Date birthday) {
        Customer customer = new Customer(name, email, phone, birthday);
        customerService.save(customer);
        return "redirect:/admin/customers";
    }
}
