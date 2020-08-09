package com.vpz.orderservice.service;

import java.util.UUID;

import com.vpz.orderservice.entity.Order;

public interface OrderService {

	public abstract Order get(UUID id);

	public abstract Order create(Order dto);

	public abstract Order update(Order dto);

	public abstract void delete(UUID id);

}
