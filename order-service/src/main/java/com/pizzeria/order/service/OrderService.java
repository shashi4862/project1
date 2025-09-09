package com.pizzeria.order.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.order.dto.OrderDTO;
import com.pizzeria.order.exception.OrderNotFoundException;
import com.pizzeria.order.model.Order;
import com.pizzeria.order.model.OrderItem;
import com.pizzeria.order.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        List<OrderItem> orderItems = orderDTO.getItems().stream()
                .map(itemDTO -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setMenuItemId(itemDTO.getMenuItemId());
                    orderItem.setQuantity(itemDTO.getQuantity());
                    return orderItem;
                }).collect(Collectors.toList());

        order.setItems(orderItems);
        
        // A placeholder for calculating the total amount
        order.setTotalAmount(BigDecimal.ZERO); 
        
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + orderId + " not found."));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
