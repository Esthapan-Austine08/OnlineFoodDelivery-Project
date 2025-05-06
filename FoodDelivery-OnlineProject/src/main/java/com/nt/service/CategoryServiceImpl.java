package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.Category;
import com.nt.model.Restaurant;
import com.nt.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category createCategory(String name, Long userId) throws Exception {
	    Restaurant restaurant = restaurantService.getRestaurantUserById(userId);
	    Category category = new Category();
	    category.setName(name);
	    category.setRestaurant(restaurant);
	    
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
		 
		return categoryRepository.findByRestaurantId(id);
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		
		 if(optionalCategory.isEmpty()) {
			 throw new Exception("Category not found");
		 }
			return optionalCategory.get();
	}
}
