package com.thaontm.project.Seconds.model;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
public class OrderItem{
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderInfo orderInfo;

    @Column(name = "quantity")
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(Product product, OrderInfo orderInfo, int quantity) {
        this.product = product;
        this.orderInfo = orderInfo;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
