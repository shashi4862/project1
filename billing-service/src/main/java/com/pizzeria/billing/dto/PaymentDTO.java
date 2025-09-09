package com.pizzeria.billing.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentDTO {
    private Long orderId;
    private BigDecimal amount;
}
