package org.ippnat.model.dto.integration;

import java.math.BigDecimal;

public record ProductResponse(Long id, String accountId, String type, BigDecimal balance) {
}
