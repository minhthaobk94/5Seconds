package com.thaontm.project.Seconds.service;

import com.thaontm.project.Seconds.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    void save(Customer customer);
    Customer findOne(Integer id);
}
