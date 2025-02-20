package com.shop.ecommerce.dtos.builders;

import com.shop.ecommerce.dtos.OrderItemDTO;
import com.shop.ecommerce.dtos.ProductDTO;
import com.shop.ecommerce.dtos.ReviewDTO;
import com.shop.ecommerce.dtos.SaleDTO;
import com.shop.ecommerce.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class ProductBuilder {
    public static ProductDTO toProductDTO(Product product) {
        List<OrderItemDTO> orderItemDTOs = product.getOrderItems().stream()
                .map(OrderItemBuilder::toOrderItemDTO)
                .collect(Collectors.toList());
        List<SaleDTO> saleDTOS = product.getSales().stream()
                .map(SaleBuilder::toSaleDTO)
                .collect(Collectors.toList());
        List<ReviewDTO> reviewDTOs = product.getReviews().stream()
                .map(ReviewBuilder::toReviewDTO)
                .collect(Collectors.toList());
        return ProductDTO.builder()
                .idProd(product.getIdProd())
                .nameProd(product.getNameProd())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .stock(product.getStock())
                .orderItems(orderItemDTOs)
                .reviews(reviewDTOs)
                .sales(saleDTOS)
                .build();
    }

    public static Product toEntity(ProductDTO productDTO) {
        List<OrderItem> ordersEn = productDTO.getOrderItems().stream()
                .map(OrderItemBuilder::toEntity)
                .collect(Collectors.toList());
        List<Sale> salesEn = productDTO.getSales().stream()
                .map(SaleBuilder::toEntity)
                .collect(Collectors.toList());
        List<Review> reviewsEn = productDTO.getReviews().stream()
                .map(ReviewBuilder::toEntity)
                .collect(Collectors.toList());
        return Product.builder()
                .idProd(productDTO.getIdProd())
                .nameProd(productDTO.getNameProd())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .category(productDTO.getCategory())
                .stock(productDTO.getStock())
                .orderItems(ordersEn)
                .reviews(reviewsEn)
                .sales(salesEn)
                .build();
    }
}
