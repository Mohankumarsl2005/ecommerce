package com.ecommerce.cart;

import java.util.List;

public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartItem addToCart(CartItem cartItem){
        return cartRepository.save(cartItem);
    }
    public List<CartItem> getCartByUser(Long userId){
        return cartRepository.findAll()
                .stream()
                .filter(item -> item.getUserId().equals(userId))
                .toList();
    }

    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }

}
