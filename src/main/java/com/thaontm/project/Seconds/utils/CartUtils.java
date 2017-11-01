package com.thaontm.project.Seconds.utils;

import com.thaontm.project.Seconds.dto.CartItemDTO;
import com.thaontm.project.Seconds.model.Product;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CartUtils {
    private static CartUtils instance;
    private HttpSession session;
    private List<CartItemDTO> cartItems;
    private static String SESSION_ATTRIBUTE_CART = "cart";

    private CartUtils(HttpSession session) {
        this.session = session;
        if (!isCartExisted()) {
            this.cartItems = new ArrayList<>();
            session.setAttribute(SESSION_ATTRIBUTE_CART, this.cartItems);
        }
    }

    public static CartUtils getInstance(HttpSession session) {
        if (instance == null) {
            instance = new CartUtils(session);
        }
        return instance;
    }

    private boolean isCartExisted() {
        if (this.session.getAttribute(SESSION_ATTRIBUTE_CART) != null) {
            return true;
        }
        return false;
    }

    public List<CartItemDTO> getCartItems() {
        return this.cartItems;
    }

    private CartItemDTO getCartItem(int productId) {
        for (CartItemDTO cartItemDTO : cartItems) {
            if (cartItemDTO.getProduct().getId() == productId) {
                return cartItemDTO;
            }
        }
        return null;
    }

    public void addToCart(Product product) {
        CartItemDTO cartItemDTO = getCartItem(product.getId());
        if (cartItemDTO == null) {
            cartItems.add(new CartItemDTO(product, Constants.QUANTITY_DEFAULT));
        } else {
            cartItemDTO.setQuantity(cartItemDTO.getQuantity() + 1);
        }
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItemDTO cartItemDTO : this.cartItems) {
            totalPrice += cartItemDTO.getTotalPrice();
        }
        return totalPrice;
    }

    public void checkout() {
        if (this.session != null) {
            this.cartItems = new ArrayList<>();
        }
    }

    public void removeCartItem(Product product) {
        CartItemDTO cartItemDTO = getCartItem(product.getId());
        cartItems.remove(cartItemDTO);
    }
}
