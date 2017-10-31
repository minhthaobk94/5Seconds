package com.thaontm.project.Seconds.utils;

import com.thaontm.project.Seconds.dto.CartDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CartUtils {

    public static int isExisting(int productId, HttpSession session) {
        List<CartDTO> cart = (List<CartDTO>) session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProductId() == productId)
                return i;
        }
        return -1;
    }

    public static double calculateTotal(int productId, HttpSession session) {
        List<CartDTO> cart = (List<CartDTO>) session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getQuantity() != 1) {
                return cart.get(i).getPrice() * cart.get(i).getQuantity();
            }
        }
        return (0.0);
    }
}
