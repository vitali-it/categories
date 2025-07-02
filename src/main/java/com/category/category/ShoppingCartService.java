package com.category.category;

public interface ShoppingCartService {

    ShoppingCart createCart(String customerReference);

    ShoppingCart getCart(Long cartId);

    ShoppingCart addProduct(Long cartId, Long productId, int quantity);

    ShoppingCart updateProductQuantity(Long cartId, Long productId, int newQuantity);

    ShoppingCart removeProduct(Long cartId, Long productId);

    double getTotal(Long cartId);
}
