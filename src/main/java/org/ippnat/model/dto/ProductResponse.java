package org.ippnat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "balance")
public class ProductResponse {

    private Long id;
    private String accountId;
    private String type;
    private BigDecimal balance;

}
