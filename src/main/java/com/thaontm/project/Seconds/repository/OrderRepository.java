package com.thaontm.project.Seconds.repository;

import com.thaontm.project.Seconds.model.OrderInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderInfo, Integer> {
    OrderInfo saveAndFlush(OrderInfo orderInfo);

    List<OrderInfo> findAllByOrderByCreatedDesc();
}
