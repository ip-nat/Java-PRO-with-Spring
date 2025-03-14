package org.ippnat.service;

import org.ippnat.model.dto.PaymentRequest;
import org.ippnat.model.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse getProduct(Long productId);

    List<ProductResponse> getUserProducts(Long userId);

    ProductResponse executeTransaction(PaymentRequest request);

}
