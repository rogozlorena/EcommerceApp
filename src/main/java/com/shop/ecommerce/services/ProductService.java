package com.shop.ecommerce.services;

import com.shop.ecommerce.dtos.ProductDTO;
import com.shop.ecommerce.dtos.builders.ProductBuilder;
import com.shop.ecommerce.entities.Product;
import com.shop.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductBuilder.toEntity(productDTO);  // Convertim DTO în entitate
        Product savedProduct = productRepository.save(product);  // Salvăm produsul în baza de date
        return ProductBuilder.toProductDTO(savedProduct);  // Convertim entitatea înapoi în DTO și returnăm
    }


    public ProductDTO getProductById(long id) {
        Optional<Product> productOptional = productRepository.findById(id);  // Căutăm produsul după ID
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Product with ID " + id + " not found");  // Dacă nu există produsul
        }
        return ProductBuilder.toProductDTO(productOptional.get());  // Convertim entitatea în DTO și returnăm
    }


    public ProductDTO updateProduct(long id, ProductDTO productDTO) {
        Optional<Product> productOptional = productRepository.findById(id);  // Căutăm produsul după ID
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Product with ID " + id + " not found");  // Dacă nu există produsul
        }


        Product product = productOptional.get();
        product.setNameProd(productDTO.getNameProd());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setStock(productDTO.getStock());

        Product updatedProduct = productRepository.save(product);  // Salvăm produsul actualizat
        return ProductBuilder.toProductDTO(updatedProduct);  // Returnăm produsul actualizat în format DTO
    }


    public void deleteProduct(long id) {
        Optional<Product> productOptional = productRepository.findById(id);  // Căutăm produsul după ID
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Product with ID " + id + " not found");  // Dacă nu există produsul
        }
        productRepository.deleteById(id);  // Ștergem produsul din baza de date
    }


    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();  // Obținem toate produsele
        return products.stream()
                .map(ProductBuilder::toProductDTO)
                .collect(Collectors.toList());  // Convertim fiecare produs în DTO
    }


    public List<ProductDTO> searchProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);  // Căutăm produsele după categorie
        return products.stream()
                .map(ProductBuilder::toProductDTO)
                .collect(Collectors.toList());  // Convertim fiecare produs în DTO
    }
}
