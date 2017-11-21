package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.model.Customer;
import com.thaontm.project.Seconds.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCustomer(@RequestParam String name, @RequestParam String email, @RequestParam String phone, @RequestParam Date birthday) {
        Customer customer = new Customer(name, email, phone, birthday);
        customerService.save(customer);
        return "";
    }
}
