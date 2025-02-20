package com.shop.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShoppingCartDTO {
private long cartId;
private long userId;
private List<OrderItemDTO> orderItems;
private double total;
}
