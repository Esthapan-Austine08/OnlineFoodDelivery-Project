package com.nt.service;

import com.nt.model.Order;
import com.nt.response.PaymentResponse;
import com.stripe.exception.StripeException;




public interface PaymentService {

	public PaymentResponse createPaymentLink(Order order) throws StripeException;
		
	
}
