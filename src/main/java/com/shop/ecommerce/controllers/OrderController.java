package com.shop.ecommerce.controllers;

import com.shop.ecommerce.dtos.OrderDTO;
import com.shop.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Endpoint pentru crearea unui order
    @PostMapping("/create-order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(createdOrder);
    }

    // Endpoint pentru obținerea unui order după id
    @GetMapping("/get-order/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable long id) {
        Optional<OrderDTO> orderDTO = orderService.getOrderById(id);
        return orderDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint pentru obținerea tuturor comenzilor
    @GetMapping("/get-order")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Endpoint pentru actualizarea unui order
    @PutMapping("/update-order/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable long id, @RequestBody OrderDTO orderDTO) {
        Optional<OrderDTO> updatedOrder = orderService.updateOrder(id, orderDTO);
        return updatedOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint pentru ștergerea unui order
    @DeleteMapping("/delete-order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        if (orderService.deleteOrder(id)) {
            return ResponseEntity.noContent().build();  // Returnează 204 dacă ștergerea a avut succes
        }
        return ResponseEntity.notFound().build();  // Returnează 404 dacă nu a fost găsit order-ul
    }
}
