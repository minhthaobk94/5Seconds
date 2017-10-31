package com.thaontm.project.Seconds.dto;

public class CartDTO {
    private int productId;
    private String productName;
    private String productImgUrl;
    private double price;
    private int quantity;

    public CartDTO(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartDTO(int productId) {
        this.productId = productId;
    }

    public CartDTO() {
    }

    public CartDTO(int productId, String productName, String productImgUrl, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productImgUrl = productImgUrl;
        this.price = price;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
