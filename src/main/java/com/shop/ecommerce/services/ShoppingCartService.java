package com.shop.ecommerce.services;

import com.shop.ecommerce.dtos.ShoppingCartDTO;
import com.shop.ecommerce.dtos.builders.ShoppingCartBuilder;
import com.shop.ecommerce.entities.ShoppingCart;
import com.shop.ecommerce.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }


    public ShoppingCartDTO createShoppingCart(ShoppingCartDTO shoppingCartDTO) {

        ShoppingCart shoppingCart = ShoppingCartBuilder.toEntity(shoppingCartDTO);


        ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCart);


        return ShoppingCartBuilder.toShoppingCartDTO(savedShoppingCart);
    }


    public ShoppingCartDTO getShoppingCartById(long id) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(id);
        if (shoppingCartOptional.isPresent()) {
            return ShoppingCartBuilder.toShoppingCartDTO(shoppingCartOptional.get());
        } else {
            throw new IllegalArgumentException("ShoppingCart with ID " + id + " not found");
        }
    }


    public List<ShoppingCartDTO> getAllShoppingCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        return shoppingCarts.stream()
                .map(ShoppingCartBuilder::toShoppingCartDTO)
                .collect(Collectors.toList());
    }


    public ShoppingCartDTO updateShoppingCart(long id, ShoppingCartDTO shoppingCartDTO) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(id);
        if (shoppingCartOptional.isPresent()) {

            ShoppingCart shoppingCart = shoppingCartOptional.get();
            shoppingCart.setTotal(shoppingCartDTO.getTotal());


            ShoppingCart updatedShoppingCart = shoppingCartRepository.save(shoppingCart);


            return ShoppingCartBuilder.toShoppingCartDTO(updatedShoppingCart);
        } else {
            throw new IllegalArgumentException("ShoppingCart with ID " + id + " not found");
        }
    }


    public void deleteShoppingCart(long id) {
        if (shoppingCartRepository.existsById(id)) {
            shoppingCartRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("ShoppingCart with ID " + id + " not found");
        }
    }
}
