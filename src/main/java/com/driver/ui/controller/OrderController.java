package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService service;
	
	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDetailsResponse response=new OrderDetailsResponse();
		OrderDto dto=service.getOrderById(id);
		response.setCost(dto.getCost());
		response.setItems(dto.getItems());
		response.setOrderId(dto.getOrderId());
		response.setStatus(dto.isStatus());
		response.setUserId(dto.getOrderId());
		
		
		return response;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderDto dto=new OrderDto();
		dto.setCost(order.getCost());
		dto.setItems(order.getItems());
		dto.setUserId(order.getUserId());
		dto.setStatus(true);
		OrderDto dto1 =service.createOrder(dto);
		OrderDetailsResponse  res=new OrderDetailsResponse ();
		res.setCost(dto1.getCost());
		res.setItems(dto1.getItems());
		res.setOrderId(dto1.getOrderId());
		res.setStatus(dto1.isStatus());
	    res.setUserId(dto1.getUserId());
		
		
		
		return res;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto dto=new OrderDto();
		dto.setCost(order.getCost());
		dto.setItems(order.getItems());
		dto.setUserId(order.getUserId());
		dto.setOrderId(id);
		OrderDto dto1 =service.createOrder(dto);
		OrderDetailsResponse  res=new OrderDetailsResponse ();
		res.setCost(dto1.getCost());
		res.setItems(dto1.getItems());
		res.setOrderId(dto1.getOrderId());
		res.setStatus(dto1.isStatus());
	    res.setUserId(dto1.getUserId());
		return res;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		  service.deleteOrder(id);
		  OperationStatusModel model=new OperationStatusModel();
		  model.setOperationName("DELETE");
		  model.setOperationResult("SUCCESS");
		return model;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDto> list=service.getOrders();
		List<OrderDetailsResponse> l=new ArrayList<>();
		for(OrderDto dto1:list)
		{
			OrderDetailsResponse  res=new OrderDetailsResponse ();
			res.setCost(dto1.getCost());
			res.setItems(dto1.getItems());
			res.setOrderId(dto1.getOrderId());
			res.setStatus(dto1.isStatus());
		    res.setUserId(dto1.getUserId());
		    l.add(res);
		}
		return l;
	}
}
