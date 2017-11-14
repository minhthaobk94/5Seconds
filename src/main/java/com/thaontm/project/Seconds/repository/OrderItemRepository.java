package com.thaontm.project.Seconds.repository;

import com.thaontm.project.Seconds.model.OrderInfo;
import com.thaontm.project.Seconds.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>{
    List<OrderItem> findAllByOrderInfo(OrderInfo orderInfo);
}
