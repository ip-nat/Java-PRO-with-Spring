package org.ippnat.model.dto;

import java.math.BigDecimal;

public record PaymentRequest (Long userId, Long productId, BigDecimal amount) {
}
