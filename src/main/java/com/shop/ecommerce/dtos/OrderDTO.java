package com.shop.ecommerce.dtos;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

// Folosesc DTO-uri pentru a nu expune entitatile in mod direct clientului
// Contine doar datele necesare pentru client
//Folosesc TDO pentru transferul de date in API
@Builder
@Data
public class OrderDTO {
    private long idOrder;
    private LocalDateTime orderDate;
    private long users_id;

}
