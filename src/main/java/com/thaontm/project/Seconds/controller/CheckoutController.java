package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.model.Customer;
import com.thaontm.project.Seconds.model.OrderInfo;
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
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CustomerService customerService;

    Customer customer;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String checkout(Map<String, Object> model, HttpSession session) {
        CartUtils.getInstance(session).checkout();
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", session.getAttribute(CartUtils.SESSION_ATTRIBUTE_CART));
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        return "checkout";
    }

    @RequestMapping(value = "/summary", method = RequestMethod.POST)
    public String getSummary(Map<String, Object> model, HttpSession session,  @RequestParam String name, @RequestParam String email, @RequestParam String phone, @RequestParam("birthday") String birthdaySt){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthday = format.parse(birthdaySt);
            customer = new Customer(name, email, phone, birthday, null);
            model.put("customer", customer);
            model.put("cart", session.getAttribute(CartUtils.SESSION_ATTRIBUTE_CART));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "checkout_summary";
    }

    @RequestMapping(value = "/bill", method = RequestMethod.POST)
    public String getBill(Map<String, Object> model, @RequestParam("order_note") String note, HttpSession session) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        String created = dtf.format(now);
        OrderInfo orderInfo = new OrderInfo(customer, customer.getEmail(), CartUtils.getInstance(session).getTotalPrice(), note, new Date(), "pending", null);
        customerService.save(customer);
        model.put("note", note);
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", session.getAttribute(CartUtils.SESSION_ATTRIBUTE_CART));
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        model.put("customer", customer);
        return "bill";
    }
}
