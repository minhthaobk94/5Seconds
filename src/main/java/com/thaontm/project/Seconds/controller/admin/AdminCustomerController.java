package com.thaontm.project.Seconds.controller.admin;

import com.thaontm.project.Seconds.model.Customer;
import com.thaontm.project.Seconds.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCustomer(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("birthday") String birthdaySt) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthday = format.parse(birthdaySt);
            Customer customer = new Customer(name, email, phone, birthday);
            customerService.save(customer);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/customer/";
    }
}
