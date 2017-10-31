package com.thaontm.project.Seconds.dto;

import com.thaontm.project.Seconds.model.Product;

public class CartItemDTO {
    private Product product;
    private int quantity = 0;

    public CartItemDTO() {
    }

    public CartItemDTO(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return this.product.getPrice() * this.quantity;
    }
}
