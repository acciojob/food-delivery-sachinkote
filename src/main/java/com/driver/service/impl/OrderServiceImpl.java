package com.driver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;

class OrderServiceImpl implements OrderService{
	@Autowired 
	OrderRepository repo;

	@Override
	public OrderDto createOrder(OrderDto order) {
		// TODO Auto-generated method stub
		OrderEntity o=new OrderEntity();
		o.setCost(order.getCost());
		o.setItems(order.getItems());
		o.setOrderId(order.getOrderId());
		o.setStatus(order.isStatus());
		o.setCost(order.getCost());
		o.setUserId(order.getOrderId());
		repo.save(o);
		
		
		
		
		return order;
	}

	@Override
	public OrderDto getOrderById(String orderId) throws Exception {
		// TODO Auto-generated method stub
		 OrderDto dto=new OrderDto();
		 OrderEntity o=repo.findByOrderId(orderId);
		 dto.setCost(o.getCost());
		 dto.setId(o.getId());
		 dto.setItems(o.getItems());
		 dto.setOrderId(orderId);
		 dto.setStatus(o.isStatus());
		 dto.setUserId(o.getOrderId());
		
		 return null;
	}

	@Override
	public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
		// TODO Auto-generated method stub
		 OrderEntity o=repo.findByOrderId(orderId);
		 o.setCost(order.getCost());
		 o.setItems(order.getItems());
		 o.setOrderId(order.getOrderId());
		 o.setStatus(order.isStatus());
		 o.setUserId(order.getUserId());
		 repo.save(o);
		return order;
	}

	@Override
	public void deleteOrder(String orderId) throws Exception {
		// TODO Auto-generated method stub
		 OrderEntity o=repo.findByOrderId(orderId);
		repo.deleteById(o.getId());
	}

	@Override
	public List<OrderDto> getOrders() {
		// TODO Auto-generated method stub
		List<OrderEntity> list=(List<OrderEntity>) repo.findAll();
		List<OrderDto> l=new ArrayList<>();
		for(OrderEntity o:list)
		{
			OrderDto dto=new OrderDto();
			 dto.setCost(o.getCost());
			 dto.setId(o.getId());
			 dto.setItems(o.getItems());
			 dto.setOrderId(o.getOrderId());
			 dto.setStatus(o.isStatus());
			 dto.setUserId(o.getOrderId()); 
			 l.add(dto);
			
		}
		
		return l;
	}
	
}