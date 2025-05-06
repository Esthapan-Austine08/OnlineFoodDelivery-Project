package com.nt.request;

import java.util.List;

import com.nt.model.Address;
import com.nt.model.ContactInformation;

import lombok.Data;


@Data
public class CreateRestaurantRequest {

	private Long id;
	private String name;
	private String description;
	private String cuisineType;
	private boolean open;
	private Address address;
	private ContactInformation contactInformation;
	private String openingHours;
	private List<String> images;
}
