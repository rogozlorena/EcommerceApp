package com.shop.ecommerce.repositories;

import com.shop.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);// metoda findByCategory face parte din Spring Data JPA -> nu trebuie sa implementez aceasta interogare manual

}
