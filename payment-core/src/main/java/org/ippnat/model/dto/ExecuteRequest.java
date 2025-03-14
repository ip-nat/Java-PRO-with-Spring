package org.ippnat.model.dto;

import java.math.BigDecimal;

public record ExecuteRequest (Long userId, Long productId, BigDecimal amount) {
}
