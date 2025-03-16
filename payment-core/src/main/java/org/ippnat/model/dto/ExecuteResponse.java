package org.ippnat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
public class ExecuteResponse {

    private BigInteger accountId;
    private BigDecimal balance;

}
