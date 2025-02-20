package com.shop.ecommerce.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderItemDTO {
    private Long id;
    private Long cartId;
    private Long productId;
    private int quantity;
}
