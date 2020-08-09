package com.vpz.orderservice.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vpz.orderservice.entity.Order;
import com.vpz.orderservice.repository.OrderRepository;
import com.vpz.orderservice.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;

    public Order get(UUID id) {
        Order item = null;
        try {
            item = orderRepository.findById(id).get();
        } catch (Exception e) {
            log.error("order not found");
        }
        return item;
    }

    public Order create(Order order) {
        Order item = null;
        try {
            item = orderRepository.save(order);
        } catch (Exception e) {
            log.error("Error while creating order");
        }
        return item;
    }

    public Order update(Order order) {
        Order item = null;
        try {
            item = orderRepository.save(order);
        } catch (Exception e) {
            log.error("Error updating order");
        }
        return item;
    }

    public void delete(UUID id) {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting order");
        }
    }
}
