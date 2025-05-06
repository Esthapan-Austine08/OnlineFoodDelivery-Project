package com.nt.service;

import java.util.List;

import com.nt.model.Category;
import com.nt.model.Food;
import com.nt.model.Restaurant;
import com.nt.request.CreateFoodRequest;

public interface FoodService {

	public Food createFood(CreateFoodRequest req,Category category,Restaurant restaurant);
	
	void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantsFood(Long restaurantId,boolean isVegitarain,boolean isNonVeg,
																	   boolean isSeasonal,String foodCategory);
	
	public List<Food> searchFood(String keyword) throws Exception;
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
