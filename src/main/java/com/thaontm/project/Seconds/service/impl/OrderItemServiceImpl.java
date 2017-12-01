package com.thaontm.project.Seconds.service.impl;

import com.thaontm.project.Seconds.model.OrderInfo;
import com.thaontm.project.Seconds.model.OrderItem;
import com.thaontm.project.Seconds.repository.OrderItemRepository;
import com.thaontm.project.Seconds.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAllByOrderInfo(OrderInfo orderInfo) {
        return orderItemRepository.findAllByOrderInfo(orderInfo);
    }

    @Override
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
