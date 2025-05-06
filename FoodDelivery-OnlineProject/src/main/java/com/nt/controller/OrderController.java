package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.Order;
import com.nt.model.User;
import com.nt.request.OrderRequest;
import com.nt.response.PaymentResponse;
import com.nt.service.OrderService;
import com.nt.service.PaymentService;
import com.nt.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/order")
	public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest req,
																			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Order order = orderService.createOrder(req, user);
		PaymentResponse res = paymentService.createPaymentLink(order);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/order/user")
	public ResponseEntity<List<Order>> getOrderHistory(
																			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		List<Order> orders = orderService.getUsersOrder(user.getId());
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	
	@DeleteMapping("/order/{id}/remove")
	public ResponseEntity<Void> deleteOrderById(
			                                                                  @PathVariable Long id,
																			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
	        orderService.cancelOrder(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
