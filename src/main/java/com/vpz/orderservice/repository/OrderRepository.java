package com.vpz.orderservice.repository;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vpz.orderservice.entity.Order;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
