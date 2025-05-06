package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
