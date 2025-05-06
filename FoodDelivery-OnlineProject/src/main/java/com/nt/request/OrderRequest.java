package com.nt.request;

import com.nt.model.Address;

import lombok.Data;

@Data
public class OrderRequest {
 
	private Long restaurantId;
	private Address deliveryAddress;
}
