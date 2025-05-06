package com.nt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.Cart;
import com.nt.model.CartItem;
import com.nt.model.Food;
import com.nt.model.User;
import com.nt.repository.CartItemRepository;
import com.nt.repository.CartRepository;
import com.nt.repository.UserRepository;
import com.nt.request.AddCartItemRequest;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private FoodService foodService;
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Override
	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Food food = foodService.findFoodById(req.getFoodId());
		Cart cart = cartRepository.findByCustomerId(user.getId());
		
		for(CartItem cartItem : cart.getItems()) {
			if(cartItem.getFood().equals(food)) {
				int newQuantity = cartItem.getQuantity()+req.getQuantity();
				return updateCartItemQuantity(cartItem.getId(), newQuantity);
			}
		}
		
		CartItem newCartItem = new CartItem();
		newCartItem.setFood(food);
		newCartItem.setCart(cart);
		newCartItem.setQuantity(req.getQuantity());
		newCartItem.setIngredients(req.getIngredients());
		newCartItem.setTotalPrice(req.getQuantity()*food.getPrice());
		
		CartItem savedCartItem = cartItemRepository.save(newCartItem);
		cart.getItems().add(savedCartItem);
		return savedCartItem;
	}

	@Override
	public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
	 if(cartItemOptional.isEmpty()) {
		 throw new Exception("Cart item not found");
	 }
	 CartItem item = cartItemOptional.get();
	 item.setQuantity(quantity);
	 item.setTotalPrice(item.getFood().getPrice()*quantity);
		return cartItemRepository.save(item);
	}

	@Override
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Cart cart = cartRepository.findByCustomerId(user.getId());
		
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
	 if(cartItemOptional.isEmpty()) {
		 throw new Exception("Cart not found");
	 }
	    CartItem item = cartItemOptional.get();
	    cart.getItems().remove(item);
		return cartRepository.save(cart);
	}

	@Override
	public Long calculateCartTotals(Cart cart) throws Exception {
		Long total = 0L;
		
		for(CartItem cartItem : cart.getItems()) {
			total += cartItem.getFood().getPrice()*cartItem.getQuantity();
		}
		return total;
	}

	@Override
	public Cart findCartById(Long id) throws Exception {
		Optional<Cart> optionalCart = cartRepository.findById(id);
		if(optionalCart.isEmpty()) {
			throw new Exception("Cart not found with id :"+id);
		}
		return optionalCart.get();
	}

	@Override
	public Cart findCartUserById(Long userId) throws Exception {
		// User user = userService.findUserByJwtToken(jwt);
		Cart  cart = cartRepository.findByCustomerId(userId);
		cart.setTotalPrice(calculateCartTotals(cart));
		return cart;
	}

	@Override
	public Cart clearCart(Long userId) throws Exception {
	//  User user = userService.findUserByJwtToken(jwt);
		Cart cart = findCartUserById(userId);
		cart.getItems().clear();
		return cartRepository.save(cart);
	}

}
