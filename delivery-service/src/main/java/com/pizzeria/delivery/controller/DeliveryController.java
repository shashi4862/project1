package com.pizzeria.delivery.controller;

import com.pizzeria.delivery.dto.DeliveryDTO;
import com.pizzeria.delivery.dto.DeliveryStatusUpdateDTO;
import com.pizzeria.delivery.model.Delivery;
import com.pizzeria.delivery.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/assign")
    public ResponseEntity<Delivery> assignDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        Delivery newDelivery = deliveryService.assignDelivery(deliveryDTO);
        return new ResponseEntity<>(newDelivery, HttpStatus.CREATED);
    }

    @PutMapping("/{deliveryId}/status")
    public ResponseEntity<Delivery> updateDeliveryStatus(@PathVariable Long deliveryId, @RequestBody DeliveryStatusUpdateDTO statusDTO) {
        Delivery updatedDelivery = deliveryService.updateDeliveryStatus(deliveryId, statusDTO);
        return new ResponseEntity<>(updatedDelivery, HttpStatus.OK);
    }
    
    @GetMapping("/{deliveryId}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long deliveryId) {
        Delivery delivery = deliveryService.getDeliveryById(deliveryId);
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        List<Delivery> deliveries = deliveryService.getAllDeliveries();
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }
}
