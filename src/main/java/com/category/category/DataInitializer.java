package com.category.category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            CategoryRepository categorieRepository,
            ProductRepository produitRepository,
            ShoppingCartRepository cartRepository
    ) {
        return args -> {
            Category electronics = new Category();
            electronics.setName("Électronique");
            electronics.setDescription("Appareils électroniques");

            Category books = new Category();
            books.setName("Livres");
            books.setDescription("Tous les types de livres");

            categorieRepository.saveAll(List.of(electronics, books));

            Category phones = new Category();
            phones.setName("Téléphones");
            phones.setDescription("Smartphones récents");
            phones.setParent(electronics);

            Category laptops = new Category();
            laptops.setName("Ordinateurs");
            laptops.setDescription("PC portables et fixes");
            laptops.setParent(electronics);

            categorieRepository.saveAll(List.of(phones, laptops));

            // -----------------------------
            // Create Products
            // -----------------------------
            Product iphone = new Product();
            iphone.setNom("iPhone 14");
            iphone.setPrice(999.99);
            iphone.setQuantityStock(20);

            Product macbook = new Product();
            macbook.setNom("MacBook Pro M2");
            macbook.setPrice(1999.99);
            macbook.setQuantityStock(5);

            Product book = new Product();
            book.setNom("Clean Code");
            book.setPrice(42.0);
            book.setQuantityStock(100);

            produitRepository.saveAll(List.of(iphone, macbook, book));

            phones.setProducts(Set.of(iphone));
            laptops.setProducts(Set.of(macbook));
            books.setProducts(Set.of(book));

            categorieRepository.saveAll(List.of(phones, laptops, books));

            ShoppingCart cart = new ShoppingCart();
            cart.setCustomerReference("client123");

            CartItem item1 = new CartItem();
            item1.setProduct(iphone);
            item1.setQuantity(2);
            item1.setCart(cart);

            CartItem item2 = new CartItem();
            item2.setProduct(book);
            item2.setQuantity(1);
            item2.setCart(cart);

            cart.setItems(List.of(item1, item2));

            cartRepository.save(cart);

            System.out.println("✅ Données de test insérées avec succès !");
            System.out.println("🛒 Panier total : " + cart.getTotal() + " €");
        };
    }
}
