package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.model.Customer;
import com.thaontm.project.Seconds.service.CustomerService;
import com.thaontm.project.Seconds.utils.CartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCustomer(Map<String, Object> model, HttpSession session,  @RequestParam String name, @RequestParam String email, @RequestParam String phone, @RequestParam("birthday") String birthdaySt) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthday = format.parse(birthdaySt);
            Customer customer = new Customer(name, email, phone, birthday, null);
            customerService.save(customer);
            model.put("customer", customerService.findOne(customer.getId()));
            model.put("cart", session.getAttribute(CartUtils.SESSION_ATTRIBUTE_CART));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "checkout_summary";
    }
}
