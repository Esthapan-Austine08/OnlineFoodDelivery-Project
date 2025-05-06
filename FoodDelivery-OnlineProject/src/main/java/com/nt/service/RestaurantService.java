package com.nt.service;

import java.util.List;

import com.nt.dto.RestaurantDto;
import com.nt.model.Restaurant;
import com.nt.model.User;
import com.nt.request.CreateRestaurantRequest;

public interface RestaurantService {

	public Restaurant createRestaurant(CreateRestaurantRequest req,User user);
	
	public Restaurant updateRestaurant(Long restaurantId,CreateRestaurantRequest updatedRestaurant) throws Exception;
	
	public void deleteRestaurant(Long restaurantId) throws Exception;
	
	public List<Restaurant> getAllRestaurant();
	
	public List<Restaurant> searchRestaurant(String keyword);
	
	public Restaurant findRestaurantById(Long id) throws Exception;
	
	public Restaurant getRestaurantUserById(Long userId) throws Exception;
	
	public RestaurantDto addFavorites(Long restaurantId,User user) throws Exception;
	
	public Restaurant updateRestaurantStatus(Long id) throws Exception;
}
