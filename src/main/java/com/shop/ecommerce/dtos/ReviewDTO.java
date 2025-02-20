package com.shop.ecommerce.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class ReviewDTO {
    private long idReview;
    private LocalDateTime reviewDate;
    private int rating;
    private String comment;
    private long user_id;
    private long product_id;
}
