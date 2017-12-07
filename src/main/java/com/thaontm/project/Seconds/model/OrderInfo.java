package com.thaontm.project.Seconds.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_info")
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cus_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "shipping_address")
    private String shipping_address;

    @Column(name = "total")
    private double total;

    @Column(name = "note")
    private String note;

    @Column(name = "created")
    private Date created;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "orderInfo")
    private List<OrderItem> orderItems;

    public OrderInfo() {
    }

    public OrderInfo(Customer customer, String shipping_address, double total, String note, Date created, String status, List<OrderItem> orderItems) {
        this.customer = customer;
        this.shipping_address = shipping_address;
        this.total = total;
        this.note = note;
        this.created = created;
        this.status = status;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
