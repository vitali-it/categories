package com.category.category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(CategoryRepository categorieRepository, ProductRepository produitRepository) {
        return args -> {

            Category electronics = new Category();
            electronics.setName("Électronique");
            electronics.setDescription("Appareils électroniques");

            Category clothing = new Category();
            clothing.setName("Vêtements");
            clothing.setDescription("Vêtements pour hommes et femmes");

            Category books = new Category();
            books.setName("Livres");
            books.setDescription("Tous les types de livres");

            categorieRepository.saveAll(List.of(electronics, clothing, books));

            Category phones = new Category();
            phones.setName("Téléphones");
            phones.setDescription("Smartphones récents");
            phones.setParent(electronics);

            Category laptops = new Category();
            laptops.setName("Ordinateurs portables");
            laptops.setDescription("PC et Macbooks");
            laptops.setParent(electronics);

            Category men = new Category();
            men.setName("Hommes");
            men.setDescription("Vêtements pour hommes");
            men.setParent(clothing);

            Category women = new Category();
            women.setName("Femmes");
            women.setDescription("Vêtements pour femmes");
            women.setParent(clothing);

            categorieRepository.saveAll(List.of(phones, laptops, men, women));

            Product iphone = new Product();
            iphone.setNom("iPhone 14");
            iphone.setPrice(999.99);
            iphone.setQuantityStock(10);

            Product macbook = new Product();
            macbook.setNom("MacBook Pro M2");
            macbook.setPrice(1999.99);
            macbook.setQuantityStock(5);

            Product tshirt = new Product();
            tshirt.setNom("T-Shirt coton");
            tshirt.setPrice(19.99);
            tshirt.setQuantityStock(50);

            Product novel = new Product();
            novel.setNom("Nom");
            novel.setPrice(12.49);
            novel.setQuantityStock(30);

            produitRepository.saveAll(List.of(iphone, macbook, tshirt, novel));

            phones.setProducts(Set.of(iphone));
            laptops.setProducts(Set.of(macbook));
            men.setProducts(Set.of(tshirt));
            books.setProducts(Set.of(novel));

            categorieRepository.saveAll(List.of(phones, laptops, men, books));

            System.out.println("✅ Données de test insérées dans H2 !");
        };
    }
}
