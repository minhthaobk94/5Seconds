package com.thaontm.project.Seconds.model;

import java.io.Serializable;

public class OrderItemId implements Serializable {
    private int product;
    private int orderInfo;

    public int hashCode() {
        return orderInfo + product;
    }

    public boolean equals(Object o) {
        if (o instanceof OrderItemId) {
            OrderItemId orderItemId = (OrderItemId) o;
            return (orderItemId.orderInfo == this.orderInfo && orderItemId.product == this.product);
        }
        return false;
    }
}
