package com.nt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.Address;
import com.nt.model.Cart;
import com.nt.model.CartItem;
import com.nt.model.Order;
import com.nt.model.OrderItem;
import com.nt.model.Restaurant;
import com.nt.model.User;
import com.nt.repository.AddressRepository;
import com.nt.repository.OrderItemRepository;
import com.nt.repository.OrderRepository;
import com.nt.repository.UserRepository;
import com.nt.request.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private CartService cartService;

	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {
		Address shippAddress = order.getDeliveryAddress();
		Address savedAddress = addressRepository.save(shippAddress);
		
	  if(!user.getAddresses().contains(savedAddress)) {
		  user.getAddresses().add(savedAddress);
		  userRepository.save(user);
		  
	 }
	  Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
	  
	  
	  Order createdOrder = new Order();
	  createdOrder.setCustomer(user);
	  createdOrder.setCreatedAt(new Date());
	  createdOrder.setOrderStatus("PENDING");
	  createdOrder.setDeliverAddress(savedAddress);
	  createdOrder.setRestaurant(restaurant);
	  
	  
	  Cart cart = cartService.findCartUserById(user.getId());
	  List<OrderItem> orderItems = new ArrayList<>();
	  
	  for(CartItem cartItem : cart.getItems()) {
		  OrderItem orderItem = new OrderItem();
		  orderItem.setFood(cartItem.getFood());
		  orderItem.setIngredients(cartItem.getIngredients());
		  orderItem.setQuantity(cartItem.getQuantity());
		  orderItem.setTotalPrice(cartItem.getTotalPrice());
		  
		  OrderItem savedOrderItem = orderItemRepository.save(orderItem);
		  orderItems.add(savedOrderItem);
		  }
	  Long totalPrice = cartService.calculateCartTotals(cart);
	     createdOrder.setItem(orderItems);
	     createdOrder.setTotalAmount(totalPrice);
	     
	    Order savedOrder = orderRepository.save(createdOrder);
	    restaurant.getOrders().add(savedOrder);
	  return createdOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderSatus) throws Exception {
		Order order = findOrderById(orderId);
		if(orderSatus.equals("OUT_FOR_DELIVERY")
				    || orderSatus.equals("DELIVERED")
				    || orderSatus.equals("COMPLETED")
				    || orderSatus.equals("PENDING")
		) {
		    order.setOrderStatus(orderSatus);
		      return orderRepository.save(order);
		}
		  throw new Exception("Please select a valid order status");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		Order order = findOrderById(orderId);
	    orderRepository.deleteById(orderId);
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
	  if(orderStatus!=null) {
		  orders = orders.stream().filter(order ->
		                                                order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
	  }
		return orders;
	}

	@Override
	public Order findOrderById(Long userId) throws Exception {
		Optional<Order> optionalOrder = orderRepository.findById(userId);
	 if(optionalOrder.isEmpty()) {
		 throw new Exception("Order not found");
	 }
		return optionalOrder.get();
	}
}
