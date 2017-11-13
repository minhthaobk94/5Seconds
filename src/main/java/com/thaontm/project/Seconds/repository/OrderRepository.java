package com.thaontm.project.Seconds.repository;

import com.thaontm.project.Seconds.model.OrderInfo;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderInfo, Integer> {
}
