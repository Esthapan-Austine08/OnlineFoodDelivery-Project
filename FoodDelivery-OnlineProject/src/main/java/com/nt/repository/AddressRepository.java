package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
