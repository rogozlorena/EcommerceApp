package com.shop.ecommerce.services;

import com.shop.ecommerce.dtos.OrderDTO;
import com.shop.ecommerce.dtos.builders.OrderBuilder;
import com.shop.ecommerce.entities.Order;
import com.shop.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Creaza un order nou
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = OrderBuilder.toEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return OrderBuilder.toOrderDTO(savedOrder);
    }

    // Gaseste un order după id
    public Optional<OrderDTO> getOrderById(long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(OrderBuilder::toOrderDTO);
    }

    // Obține toate comenzile
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderBuilder::toOrderDTO)
                .toList();
    }

    // Actualizeaza un order
    @Transactional
    public Optional<OrderDTO> updateOrder(long id, OrderDTO orderDTO) {
        if (orderRepository.existsById(id)) {
            Order order = OrderBuilder.toEntity(orderDTO);
            order.setIdOrder(id);  // Se asigură că actualizăm doar id-ul corect
            Order updatedOrder = orderRepository.save(order);
            return Optional.of(OrderBuilder.toOrderDTO(updatedOrder));
        }
        return Optional.empty();
    }

    // Șterge un order
    @Transactional
    public boolean deleteOrder(long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
