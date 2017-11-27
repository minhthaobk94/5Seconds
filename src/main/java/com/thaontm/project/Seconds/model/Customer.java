package com.thaontm.project.Seconds.model;

import com.thaontm.project.Seconds.utils.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @OneToMany(mappedBy = "customer")
    private List<OrderInfo> orders;

    public Customer() {
    }

    public Customer(String customer_name, String phone, String email, Date birthday, List<OrderInfo> orders) {
        this.customer_name = customer_name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.orders = orders;
    }

    public Customer(String customer_name, String phone, String email, Date birthday) {
        this.customer_name = customer_name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return (new StringUtils().convertDateToString(birthday));
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<OrderInfo> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderInfo> orders) {
        this.orders = orders;
    }
}
