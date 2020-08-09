package com.vpz.orderservice.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vpz.orderservice.constants.Constants;
import com.vpz.orderservice.entity.Order;
import com.vpz.orderservice.service.OrderService;
import com.vpz.orderservice.service.impl.OrderServiceImpl;

@RestController
@RequestMapping(Constants.BASE_URL)
public class OrderController {

    private Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderService itemService;

    @GetMapping(value = Constants.GET_ORDER_URL)
    public ResponseEntity<?> getOrder(@PathVariable("id") UUID id) {
        Order order = null;
        try {
            if(StringUtils.isEmpty(id))
                throw new Exception("Order id can not be empty");
            order = itemService.get(id);
        } catch (Exception e) {
            log.error("Error while getting order details for order " + id);
        }
        if (order != null)
            return new ResponseEntity<Order>(order, HttpStatus.OK);
        return new ResponseEntity<String>("order not found", HttpStatus.NOT_FOUND);

    }

    @PostMapping(value = Constants.ORDER_URL, consumes = "application/json")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        Order response = null;
        try {
            response = itemService.create(order);
        } catch (Exception e) {
            log.error("Error while saving order customer " + order.getCustomerName());
        }
        if (response != null)
            return new ResponseEntity<Order>(response, HttpStatus.CREATED);
        return new ResponseEntity<Order>(response, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = Constants.ORDER_URL, consumes = "application/json")
    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
        try {
            itemService.update(order);
        } catch (Exception e) {
            log.error("Error updating order for customer " + order.getCustomerName());
            return new ResponseEntity<String>("update failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("order updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping(value = Constants.GET_ORDER_URL)
    public ResponseEntity<?> deleteOrder(@PathVariable("id") UUID id) {
        try {
            itemService.delete(id);
        } catch (Exception e) {
            log.error("Error deleting order " + id);
            return new ResponseEntity<String>("Delete failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Order deleted successfully", HttpStatus.OK);
    }
}
