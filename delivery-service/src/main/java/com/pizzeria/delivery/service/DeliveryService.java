package com.pizzeria.delivery.service;

import com.pizzeria.delivery.dto.DeliveryDTO;
import com.pizzeria.delivery.dto.DeliveryStatusUpdateDTO;
import com.pizzeria.delivery.exception.DeliveryNotFoundException;
import com.pizzeria.delivery.model.Delivery;
import com.pizzeria.delivery.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Delivery assignDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(deliveryDTO.getOrderId());
        delivery.setDriverName(deliveryDTO.getDriverName());
        delivery.setDeliveryStatus("ASSIGNED");
        delivery.setEstimatedDeliveryTime(LocalDateTime.now().plusMinutes(30));
        return deliveryRepository.save(delivery);
    }

    public Delivery updateDeliveryStatus(Long deliveryId, DeliveryStatusUpdateDTO statusDTO) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
            .orElseThrow(() -> new DeliveryNotFoundException("Delivery with ID " + deliveryId + " not found."));
        delivery.setDeliveryStatus(statusDTO.getDeliveryStatus());
        return deliveryRepository.save(delivery);
    }

    public Delivery getDeliveryById(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
            .orElseThrow(() -> new DeliveryNotFoundException("Delivery with ID " + deliveryId + " not found."));
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
}
