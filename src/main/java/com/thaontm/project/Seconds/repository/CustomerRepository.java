package com.thaontm.project.Seconds.repository;

import com.thaontm.project.Seconds.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer saveAndFlush(Customer customer);
}
