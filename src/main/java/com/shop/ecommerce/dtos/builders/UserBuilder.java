package com.shop.ecommerce.dtos.builders;

import com.shop.ecommerce.dtos.*;
import com.shop.ecommerce.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserBuilder {
    public static UserDTO toUserDTO(User user) {
        List<OrderDTO> orderDTOs = user.getOrders().stream()
                .map(OrderBuilder::toOrderDTO)
                .collect(Collectors.toList());
        List<SaleDTO> saleDTOS = user.getSales().stream()
                .map(SaleBuilder::toSaleDTO)
                .collect(Collectors.toList());
        List<ReviewDTO> reviewDTOs = user.getReviews().stream()
                .map(ReviewBuilder::toReviewDTO)
                .collect(Collectors.toList());
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .address(user.getAddress())
                .dateOfBirth(user.getDateOfBirth())
                .isAdmin(user.isAdmin())
                .orders(orderDTOs)
                .reviews(reviewDTOs)
                .sales(saleDTOS)
                .shoppingCart(user.getShoppingCart() != null ?
                        ShoppingCartBuilder.toShoppingCartDTO(user.getShoppingCart())
                        : null)
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        List<Order> ordersEn = userDTO.getOrders() != null ? userDTO.getOrders().stream()
                .map(OrderBuilder::toEntity)
                .collect(Collectors.toList()) : new ArrayList<>();
        List<Sale> salesEn = userDTO.getSales() != null ? userDTO.getSales().stream()
                .map(SaleBuilder::toEntity)
                .collect(Collectors.toList()) : new ArrayList<>();
        List<Review> reviewsEn = userDTO.getReviews() != null ? userDTO.getReviews().stream()
                .map(ReviewBuilder::toEntity)
                .collect(Collectors.toList()) : new ArrayList<>();
        return User.builder()
                .id(userDTO.getId())
                .lastName(userDTO.getLastName())
                .firstName(userDTO.getFirstName())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .isAdmin(userDTO.isAdmin())
                .orders(ordersEn)
                .reviews(reviewsEn)
                .sales(salesEn)
                .shoppingCart(userDTO.getShoppingCart() != null ?
                        ShoppingCartBuilder.toEntity(userDTO.getShoppingCart())
                        : null)
                .build();
    }

}
