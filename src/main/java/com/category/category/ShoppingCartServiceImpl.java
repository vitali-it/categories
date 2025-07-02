package com.category.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository cartRepository;

    private final ProductRepository productRepository;

    @Override
    public ShoppingCart createCart(String customerReference) {
        ShoppingCart cart = new ShoppingCart();
        cart.setCustomerReference(customerReference);
        return cartRepository.save(cart);
    }

    @Override
    public ShoppingCart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow();
    }

    @Override
    public ShoppingCart addProduct(Long cartId, Long productId, int quantity) {
        ShoppingCart cart = getCart(cartId);
        Product product = productRepository.findById(productId).orElseThrow();

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setCart(cart);
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    @Override
    public ShoppingCart updateProductQuantity(Long cartId, Long productId, int newQuantity) {
        ShoppingCart cart = getCart(cartId);

        cart.getItems().stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .ifPresent(item -> item.setQuantity(newQuantity));

        return cartRepository.save(cart);
    }

    @Override
    public ShoppingCart removeProduct(Long cartId, Long productId) {
        ShoppingCart cart = getCart(cartId);

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));

        return cartRepository.save(cart);
    }

    @Override
    public double getTotal(Long cartId) {
        ShoppingCart cart = getCart(cartId);
        return cart.getTotal();
    }
}
