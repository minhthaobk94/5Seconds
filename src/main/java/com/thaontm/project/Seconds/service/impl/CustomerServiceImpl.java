package com.thaontm.project.Seconds.service.impl;

import com.thaontm.project.Seconds.model.Customer;
import com.thaontm.project.Seconds.repository.CustomerRepository;
import com.thaontm.project.Seconds.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findOne(Integer id) {
        return customerRepository.findOne(id);
    }
}
