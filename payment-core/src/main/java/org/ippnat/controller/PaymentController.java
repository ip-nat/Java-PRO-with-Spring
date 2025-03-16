package org.ippnat.controller;

import lombok.extern.slf4j.Slf4j;
import org.ippnat.model.dto.ExecuteRequest;
import org.ippnat.model.dto.ExecuteResponse;
import org.ippnat.model.dto.integration.ProductResponse;
import org.ippnat.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/users/{userId}/products")
    public List<ProductResponse> getUserProducts(@PathVariable Long userId) {
        log.info("Получение всех продуктов пользователя с ID='{}'", userId);
        return paymentService.getUserProducts(userId);
    }

    @GetMapping("/users/{userId}/products/{productId}")
    public ProductResponse getUserProduct(@PathVariable Long userId,
                                          @PathVariable Long productId) {
        log.info("Получение продукта пользователя с ID='{}'", userId);
        return paymentService.getUserProduct(userId, productId);
    }

    @PostMapping("/execute")
    public ExecuteResponse executePayment(@RequestBody ExecuteRequest request) {
        log.info("Проведение платежа для пользователя с ID='{}' по продукту '{}' на сумму '{}'", request.userId(), request.productId(), request.amount());
        return paymentService.executePayment(request);
    }

}
