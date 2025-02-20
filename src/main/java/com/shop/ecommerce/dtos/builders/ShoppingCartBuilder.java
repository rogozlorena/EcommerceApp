package com.shop.ecommerce.dtos.builders;

import com.shop.ecommerce.dtos.OrderItemDTO;
import com.shop.ecommerce.dtos.ShoppingCartDTO;
import com.shop.ecommerce.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartBuilder {
    public static ShoppingCartDTO toShoppingCartDTO(ShoppingCart shoppingCart) {
        List<OrderItemDTO> orderItemDTOs = shoppingCart.getOrderItems().stream()
                .map(OrderItemBuilder::toOrderItemDTO)
                .collect(Collectors.toList());
        return ShoppingCartDTO.builder()
                .cartId(shoppingCart.getIdCart())
                .total(shoppingCart.getTotal())
                .userId(shoppingCart.getUser() != null ? shoppingCart.getUser().getId() : 0)
                .orderItems(orderItemDTOs)
                .build();
    }

    public static ShoppingCart toEntity(ShoppingCartDTO shoppingCartDTO) {
        List<OrderItem> ordersEn = shoppingCartDTO.getOrderItems().stream()
                .map(OrderItemBuilder::toEntity)
                .collect(Collectors.toList());
        return ShoppingCart.builder()
                .idCart(shoppingCartDTO.getCartId())
                .total(shoppingCartDTO.getTotal())
                .user(User.builder()
                        .id(shoppingCartDTO.getUserId())
                        .build())
                .orderItems(ordersEn)
                .build();
    }
}

