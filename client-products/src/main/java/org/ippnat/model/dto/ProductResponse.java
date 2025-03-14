package org.ippnat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private BigInteger accountId;
    private ProductTypeEnum type;
    private BigDecimal balance;

}
