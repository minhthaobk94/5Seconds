package com.thaontm.project.Seconds.service.impl;

import com.thaontm.project.Seconds.model.OrderInfo;
import com.thaontm.project.Seconds.repository.OrderRepository;
import com.thaontm.project.Seconds.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Override
    public OrderInfo saveAndFlush(OrderInfo orderInfo) {
        return orderRepository.saveAndFlush(orderInfo);
    }

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderInfo> findAll() {
        return (List<OrderInfo>) orderRepository.findAll();
    }

    @Override
    public OrderInfo findOne(Integer id) {
        return orderRepository.findOne(id);
    }

    @Override
    public void save(OrderInfo orderInfo) {
        orderRepository.save(orderInfo);
    }

    @Override
    public List<OrderInfo> findOrderInfoByCustomerId(Integer id) {
        return orderRepository.findOrderInfoByCustomerId(id);
    }
}
