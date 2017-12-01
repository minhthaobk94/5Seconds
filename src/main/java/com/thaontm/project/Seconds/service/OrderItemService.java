package com.thaontm.project.Seconds.service;

import com.thaontm.project.Seconds.model.OrderInfo;
import com.thaontm.project.Seconds.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAllByOrderInfo(OrderInfo orderInfo);
    void save(OrderItem orderItem);
}
