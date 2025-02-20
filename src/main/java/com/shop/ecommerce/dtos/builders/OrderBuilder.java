package com.shop.ecommerce.dtos.builders;


import com.shop.ecommerce.dtos.OrderDTO;
import com.shop.ecommerce.entities.Order;
import com.shop.ecommerce.entities.User;
//Am definit o clasa utilitara, aceasta ma ajuta sa realizez conversiile intre entitate si DTO
public class OrderBuilder {
    public static OrderDTO toOrderDTO(Order order) {
        return OrderDTO.builder()
                .idOrder(order.getIdOrder())
                .orderDate(order.getOrderDate())
                .users_id(order.getUser().getId())
                .build();
    }

    public static Order toEntity(OrderDTO orderDTO) {
        return Order.builder()
                .idOrder(orderDTO.getIdOrder())
                .orderDate(orderDTO.getOrderDate())
                .user(User.builder()
                        .id(orderDTO.getUsers_id())
                        .build())
                .build();
    }

}