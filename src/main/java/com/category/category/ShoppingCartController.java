package com.category.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService service;

    @PostMapping
    public ShoppingCart createCart(@RequestParam String customerReference) {
        return service.createCart(customerReference);
    }

    @GetMapping("/{cartId}")
    public ShoppingCart getCart(@PathVariable Long cartId) {
        return service.getCart(cartId);
    }

    @PostMapping("/{cartId}/add")
    public ShoppingCart addProduct(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return service.addProduct(cartId, productId, quantity);
    }

    @PutMapping("/{cartId}/update")
    public ShoppingCart updateQuantity(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return service.updateProductQuantity(cartId, productId, quantity);
    }

    @DeleteMapping("/{cartId}/remove")
    public ShoppingCart removeProduct(
            @PathVariable Long cartId,
            @RequestParam Long productId
    ) {
        return service.removeProduct(cartId, productId);
    }

    @GetMapping("/{cartId}/total")
    public double getTotal(@PathVariable Long cartId) {
        return service.getTotal(cartId);
    }
}
