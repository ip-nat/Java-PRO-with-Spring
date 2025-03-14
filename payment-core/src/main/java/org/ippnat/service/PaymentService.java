package org.ippnat.service;

import org.ippnat.model.dto.ExecuteRequest;
import org.ippnat.model.dto.ExecuteResponse;
import org.ippnat.model.dto.integration.ProductResponse;

import java.util.List;

public interface PaymentService {

    List<ProductResponse> getUserProducts(Long userId);

    ProductResponse getUserProduct(Long userId, Long productId);

    ExecuteResponse executePayment(ExecuteRequest request);

}
