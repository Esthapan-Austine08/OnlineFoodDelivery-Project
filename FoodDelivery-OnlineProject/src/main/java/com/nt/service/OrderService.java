package com.nt.service;

import java.util.List;

import com.nt.model.Order;
import com.nt.model.User;
import com.nt.request.OrderRequest;

public interface OrderService {

	public Order createOrder(OrderRequest order,User user)throws Exception;
	
	public Order updateOrder(Long orderId,String orderSatus) throws Exception;
	
	public void cancelOrder(Long orderId) throws Exception;
	
	public List<Order> getUsersOrder(Long userId) throws Exception;
	
	public List<Order> getRestaurantsOrder(Long restaurantId,String orderStatus) throws Exception;
	
	public Order findOrderById(Long userId) throws Exception;
}
