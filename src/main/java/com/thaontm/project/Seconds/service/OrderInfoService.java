package com.thaontm.project.Seconds.service;

import com.thaontm.project.Seconds.model.OrderInfo;

import java.util.List;

public interface OrderInfoService {
    List<OrderInfo> findAll();
    OrderInfo findOne(Integer id);
    void save(OrderInfo orderInfo);
    OrderInfo saveAndFlush(OrderInfo orderInfo);
}
