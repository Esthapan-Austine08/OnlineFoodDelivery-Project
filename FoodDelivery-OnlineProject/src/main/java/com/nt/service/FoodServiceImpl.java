package com.nt.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.Category;
import com.nt.model.Food;
import com.nt.model.Restaurant;
import com.nt.repository.FoodRepository;
import com.nt.request.CreateFoodRequest;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
		Food food = new Food();
		food.setFoodCategory(category);
		food.setRestaurant(restaurant);
		food.setDescription(req.getDescription());
		food.setImages(req.getImages());
		food.setName(req.getName());
		food.setPrice(req.getPrice());
		food.setIngredients(req.getIngredients());
		food.setSeasonal(req.isSeasonal());
		food.setVegetarian(req.isVegetarian());
		food.setAvailable(req.isAvailable());
		food.setCreationDate(new Date());
		
		Food savedFood = foodRepository.save(food);
		restaurant.getFoods().add(savedFood);
		return savedFood;
	}

	@Override
	public void deleteFood(Long foodId) throws Exception {
		Food food = findFoodById(foodId);
		food.setRestaurant(null);
		foodRepository.save(food);
	}

	@Override
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarain, boolean isNonVeg, boolean isSeasonal,
			String foodCategory) {
		List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
		
		if(isVegitarain) {
			  foods=filterByVegetarian(foods,isVegitarain);
		}
		if(isNonVeg) {
			  foods=filterByNonVeg(foods,isNonVeg);
		}
		if(isSeasonal) {
			  foods=filterBySeaonal(foods,isSeasonal);
		}
		if(foodCategory!=null && !foodCategory.equals("")) {
			foods = filterByCategory(foods,foodCategory);
		}
		return foods;
	}
	private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
		return foods.stream().filter(food-> {
			if(food.getFoodCategory()!=null) {
			    return food.getFoodCategory().getName().equals(foodCategory);	
			}
			return false;
			}).collect(Collectors.toList());
	}

	private List<Food> filterBySeaonal(List<Food> foods, boolean isSeasonal) {
		return foods.stream().filter(food->food.isSeasonal()==isSeasonal).collect(Collectors.toList());
	}

	private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVeg) {
		return foods.stream().filter(food->food.isVegetarian()==false).collect(Collectors.toList());
	}

	private List<Food> filterByVegetarian(List<Food> foods, boolean isVegitarain) {
		return foods.stream().filter(food->food.isVegetarian()==isVegitarain).collect(Collectors.toList());
	}

	@Override
	public List<Food> searchFood(String keyword) throws Exception {
		return foodRepository.searchFood(keyword);
	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		Optional<Food> optionalFood = foodRepository.findById(foodId);
	 if(optionalFood.isEmpty()) {
		 throw new Exception("Food not exist .....");
	 }
		return optionalFood.get();
	}

	@Override
	public Food updateAvailabilityStatus(Long foodId) throws Exception {
		Food food = findFoodById(foodId);
		food.setAvailable(!food.isAvailable());
		return foodRepository.save(food);
	}

}
