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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.Food;
import com.nt.model.Restaurant;
import com.nt.model.User;
import com.nt.request.CreateFoodRequest;
import com.nt.response.MessageResponse;
import com.nt.service.FoodService;
import com.nt.service.RestaurantService;
import com.nt.service.UserService;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "http://localhost:5173")
public class FoodController {

	@Autowired
	private FoodService foodService;
	@Autowired
	private UserService userService;
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Food>> searchFood(@RequestParam String name ,
																			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		List<Food> foods = foodService.searchFood(name);
		
		return new ResponseEntity<>(foods,HttpStatus.CREATED);
	}
	
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian ,
												@RequestParam boolean seasonal,
												@RequestParam boolean nonveg,
												@RequestParam(required = false) String food_category,
												@PathVariable Long restaurantId,
												@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		List<Food> foods = foodService.getRestaurantsFood(restaurantId, vegetarian, nonveg, seasonal, food_category);
		
		return new ResponseEntity<>(foods,HttpStatus.OK);
}
	
	@PostMapping
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req ,
																			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
		Food foods = foodService.createFood(req, req.getCategory(), restaurant);
		
		return new ResponseEntity<>(foods,HttpStatus.CREATED);
	}
}
