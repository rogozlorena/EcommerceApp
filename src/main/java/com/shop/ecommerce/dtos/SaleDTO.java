package com.shop.ecommerce.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleDTO {
    private long idSale;
    private double newPrice;
    private double oldPrice;
    private double percent;
    private long userId;
    private long productId;
}
