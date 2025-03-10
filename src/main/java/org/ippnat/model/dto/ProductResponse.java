package org.ippnat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ippnat.entity.Product;

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

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.accountId = product.getAccountId();
        this.type = product.getType();
        this.balance = product.getBalance();
    }

}
