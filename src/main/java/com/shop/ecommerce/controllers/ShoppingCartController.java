package com.shop.ecommerce.controllers;

import com.shop.ecommerce.dtos.ShoppingCartDTO;
import com.shop.ecommerce.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCarts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


    @PostMapping("/create")
    public ResponseEntity<ShoppingCartDTO> createShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCartDTO createdShoppingCart = shoppingCartService.createShoppingCart(shoppingCartDTO);
        return ResponseEntity.ok(createdShoppingCart);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCartById(@PathVariable long id) {
        ShoppingCartDTO shoppingCart = shoppingCartService.getShoppingCartById(id);
        return ResponseEntity.ok(shoppingCart);
    }


    @GetMapping("/all")
    public ResponseEntity<List<ShoppingCartDTO>> getAllShoppingCarts() {
        List<ShoppingCartDTO> shoppingCarts = shoppingCartService.getAllShoppingCarts();
        return ResponseEntity.ok(shoppingCarts);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ShoppingCartDTO> updateShoppingCart(@PathVariable long id, @RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCartDTO updatedShoppingCart = shoppingCartService.updateShoppingCart(id, shoppingCartDTO);
        return ResponseEntity.ok(updatedShoppingCart);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteShoppingCart(@PathVariable long id) {
        shoppingCartService.deleteShoppingCart(id);
        return ResponseEntity.noContent().build();
    }
}
