package com.shop.ecommerce.dtos;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductDTO {
    private long idProd;
    private String nameProd;
    private String description;
    private double price;
    private int stock;
    private String category;

private List<OrderItemDTO> orderItems;
private List<SaleDTO> sales;
private List<ReviewDTO> reviews;

}
