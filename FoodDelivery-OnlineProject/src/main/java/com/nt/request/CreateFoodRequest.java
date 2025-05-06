package com.nt.request;

import java.util.List;

import com.nt.model.Category;
import com.nt.model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {

	private String name;
	private String description;
	private Long price;
	
	private Category category;
	private List<String> images;
	
	private Long restaurantId;
	private boolean vegetarian;
	private boolean seasonal;
	private boolean available;
	private List<IngredientsItem> ingredients;
}
