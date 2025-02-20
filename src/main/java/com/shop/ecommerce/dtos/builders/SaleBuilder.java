package com.shop.ecommerce.dtos.builders;

import com.shop.ecommerce.dtos.SaleDTO;
import com.shop.ecommerce.entities.*;
//Cu ajutorul acestei clase transform obiectele intre entitate si DTO-> clasa helper
public class SaleBuilder {
    public static SaleDTO toSaleDTO(Sale sale) {//convertim Sale in SaleDTO -> il folosim pentru transferul de date in API
        return SaleDTO.builder()
                .idSale(sale.getIdSale())
                .oldPrice(sale.getOldPrice())
                .newPrice(sale.getNewPrice())
                .percent(sale.getPercent())
                .userId(sale.getUser().getId())//Preluam doar id-ul user-ului, deoarce nu dorim sa pastram obiecte in DTO-uri, doar id-urile lor
                .productId(sale.getProduct().getIdProd())
                .build();
    }
    public static Sale toEntity(SaleDTO saleDTO) {//transformam SaleDTO in entitatea Sale->Pentru a salva datele in baza de date
        return Sale.builder()
                .idSale(saleDTO.getIdSale())
                .oldPrice(saleDTO.getOldPrice())
                .newPrice(saleDTO.getNewPrice())
                .percent(saleDTO.getPercent())
                .user(User.builder()//entitatea Sale contine un obiect User, dat DTO-ul contine doar id-ul obiectului, nu avem datele despre user, dar avem nevoie de un obiect user
                        .id(saleDTO.getUserId())// construim un obiect user doar cu id-ul
                        .build())
                .product(Product.builder()
                        .idProd(saleDTO.getProductId())
                        .build())
                .build();
    }
}
