package com.thaontm.project.Seconds.controller.admin;

import com.thaontm.project.Seconds.model.Customer;
import com.thaontm.project.Seconds.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminCustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String getCustomers(Map<String, Object> model) {
        model.put("customers", customerService.findAll());
        return "/admin/customers";
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.GET)
    public String getAddCustomer() {
        return "/admin/add_customer";
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public String addCustomer(@PathVariable("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:/admin/customers";
    }
}
