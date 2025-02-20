package com.shop.ecommerce.dtos.builders;

import com.shop.ecommerce.dtos.ReviewDTO;
import com.shop.ecommerce.entities.*;

public class ReviewBuilder {
    public static ReviewDTO toReviewDTO(Review review) {
        return ReviewDTO.builder()
                .idReview(review.getIdReview())
                .reviewDate(review.getReviewDate())
                .rating(review.getRating())
                .comment(review.getComment())
                .user_id(review.getUser().getId())
                .product_id(review.getProduct().getIdProd())
                .build();
    }
    public static Review toEntity(ReviewDTO reviewDTO) {
        return Review.builder()
                .idReview(reviewDTO.getIdReview())
                .reviewDate(reviewDTO.getReviewDate())
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
                .user(User.builder()
                        .id(reviewDTO.getUser_id())
                        .build())
                .product(Product.builder()
                        .idProd(reviewDTO.getProduct_id())
                        .build())
                .build();
    }
}
