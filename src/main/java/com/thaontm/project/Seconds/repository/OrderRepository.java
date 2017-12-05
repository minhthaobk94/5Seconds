package com.thaontm.project.Seconds.repository;

import com.thaontm.project.Seconds.model.OrderInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderInfo, Integer> {
    List<OrderInfo> findOrderInfoByCustomerId(Integer id);
    OrderInfo saveAndFlush(OrderInfo orderInfo);

}
