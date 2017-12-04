package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.dto.CartItemDTO;
import com.thaontm.project.Seconds.model.Customer;
import com.thaontm.project.Seconds.model.OrderInfo;
import com.thaontm.project.Seconds.model.OrderItem;
import com.thaontm.project.Seconds.service.CustomerService;
import com.thaontm.project.Seconds.service.OrderInfoService;
import com.thaontm.project.Seconds.service.OrderItemService;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderItemService orderItemService;

    Customer customer;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String checkout(Map<String, Object> model, HttpSession session) {
        CartUtils.getInstance(session).checkout();
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", session.getAttribute(CartUtils.SESSION_ATTRIBUTE_CART));
        return "checkout";
    }

    @RequestMapping(value = "/summary", method = RequestMethod.POST)
    public String getSummary(Map<String, Object> model, HttpSession session, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("address") String address, @RequestParam("phone") String phone, @RequestParam("birthday") String birthdaySt) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthday = format.parse(birthdaySt);
            customer = new Customer(name, email, address, phone, birthday, null);
            model.put("customer", customer);
            model.put("cart", session.getAttribute(CartUtils.SESSION_ATTRIBUTE_CART));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "checkout_summary";
    }

    @RequestMapping(value = "/bill", method = RequestMethod.POST)
    public String getBill(Map<String, Object> model, @RequestParam("order_note") String note, HttpSession session) {
        double totalPrice = CartUtils.getInstance(session).getTotalPrice();
        OrderInfo orderInfo = new OrderInfo(customer, customer.getAddress(), totalPrice, note, new Date(), "Pending", null);
        customerService.save(customer);
        orderInfoService.save(orderInfo);
        List<CartItemDTO> items = CartUtils.getInstance(session).getCartItems();
        for (CartItemDTO cartItemDTO : items) {
            OrderItem orderItem = new OrderItem(cartItemDTO.getProduct(), orderInfo, cartItemDTO.getQuantity());
            orderItemService.save(orderItem);
        }
        model.put("note", note);
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", session.getAttribute(CartUtils.SESSION_ATTRIBUTE_CART));
        model.put("cartTotalPrice", totalPrice);
        model.put("customer", customer);
        return "bill";
    }
}
