package com.nt.service;

import java.util.List;

import com.nt.model.IngredientCategory;
import com.nt.model.IngredientsItem;

public interface IngredientService {

	public IngredientCategory createIngredientCategory(String name,Long restaurantId) throws Exception;
	
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
	
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;
	
	public IngredientsItem createIngredientItem(Long restaurantId,String ingredientName,Long categoryId) throws Exception;
	
	public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId);
	
	public IngredientsItem updateStack(Long id) throws Exception;
}
