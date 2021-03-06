package com.thaontm.project.Seconds.controller;

import com.thaontm.project.Seconds.dto.CartItemDTO;
import com.thaontm.project.Seconds.model.Customer;
import com.thaontm.project.Seconds.model.OrderInfo;
import com.thaontm.project.Seconds.model.OrderItem;
import com.thaontm.project.Seconds.service.CategoryService;
import com.thaontm.project.Seconds.service.CustomerService;
import com.thaontm.project.Seconds.service.OrderInfoService;
import com.thaontm.project.Seconds.service.OrderItemService;
import com.thaontm.project.Seconds.service.ProductService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String checkout(Map<String, Object> model, HttpSession session) {
        model.put("itemsQuantity", CartUtils.getInstance(session).getItemsQuantity());
        model.put("cart", session.getAttribute(CartUtils.SESSION_ATTRIBUTE_CART));
        model.put("cartTotalPrice", CartUtils.getInstance(session).getTotalPrice());
        model.put("categories", categoryService.findAll());
        return "checkout";
    }

    public CheckoutController() {
        super();
    }

    @RequestMapping(value = "/bill", method = RequestMethod.POST)
    public String getBill(Map<String, Object> model, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("address") String address, @RequestParam("phone") String phone, @RequestParam("birthday") String birthdaySt, @RequestParam("order_note") String note, HttpSession session) {
        double totalPrice = CartUtils.getInstance(session).getTotalPrice();

        Customer customer = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthday = format.parse(birthdaySt);
            customer = new Customer(name, phone, address, email, birthday, null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Customer newCustomer = customerService.saveAndFlush(customer);

        OrderInfo orderInfo = new OrderInfo(newCustomer, customer.getAddress(), totalPrice, note, new Date(), "Pending", null);

        OrderInfo newOrderInfo = orderInfoService.saveAndFlush(orderInfo);

        List<CartItemDTO> items = CartUtils.getInstance(session).getCartItems();
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        for (CartItemDTO cartItemDTO : items) {
            orderItem.setOrderInfo(newOrderInfo);
            orderItem.setProduct(productService.findOne(cartItemDTO.getProduct().getId()));
            orderItem.setQuantity(cartItemDTO.getQuantity());
            orderItems.add(orderItem);
            orderItemService.save(orderItem);
        }
        orderInfo.setOrderItems(orderItems);
        model.put("note", note);
        model.put("cartOrder", session.getAttribute(CartUtils.SESSION_ATTRIBUTE_CART));
        model.put("total", totalPrice);
        model.put("customer", customer);
        model.put("orderInfo", newOrderInfo);
        model.put("categories", categoryService.findAll());
        return "redirect:/order/search/?orderId=" + newOrderInfo.getId();
    }
}
