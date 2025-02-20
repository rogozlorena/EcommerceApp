package com.shop.ecommerce.dtos;

import com.shop.ecommerce.entities.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {
private long id;
private String firstName;
private String lastName;
private String email;
private String password;
private String username;
private String address;
private LocalDate dateOfBirth;
private boolean isAdmin;
private List<OrderDTO> orders;
private List<SaleDTO> sales;
private List<ReviewDTO> reviews;
private ShoppingCartDTO shoppingCart;

}
