package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	public Cart findByCustomerId(Long userId);
}
