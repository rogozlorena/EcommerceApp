package com.shop.ecommerce.services;

import com.shop.ecommerce.dtos.OrderItemDTO;
import com.shop.ecommerce.dtos.builders.OrderItemBuilder;
import com.shop.ecommerce.entities.OrderItem;
import com.shop.ecommerce.entities.Product;
import com.shop.ecommerce.entities.ShoppingCart;
import com.shop.ecommerce.repositories.OrderItemRepository;
import com.shop.ecommerce.repositories.ProductRepository;
import com.shop.ecommerce.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository,
                            ShoppingCartRepository shoppingCartRepository,
                            ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
    }

    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {

        Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(orderItemDTO.getCartId());
        if (cartOptional.isEmpty()) {
            throw new IllegalArgumentException("Shopping Cart with ID " + orderItemDTO.getCartId() + " not found");
        }


        Optional<Product> productOptional = productRepository.findById(orderItemDTO.getProductId());
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Product with ID " + orderItemDTO.getProductId() + " not found");
        }

        // Convertim DTO în Entity folosind Builder
        OrderItem orderItem = OrderItemBuilder.toEntity(orderItemDTO);
        orderItem.setShoppingCart(cartOptional.get());
        orderItem.setProduct(productOptional.get());

        // Salvez în baza de date
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        // Returnez DTO-ul rezultat
        return OrderItemBuilder.toOrderItemDTO(savedOrderItem);
    }
}
