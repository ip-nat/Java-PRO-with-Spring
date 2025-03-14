package org.ippnat.service;

import lombok.extern.slf4j.Slf4j;
import org.ippnat.model.dto.ExecuteRequest;
import org.ippnat.model.dto.ExecuteResponse;
import org.ippnat.model.dto.integration.ProductResponse;
import org.ippnat.model.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${integration.clients.products-service.url}")
    private String productsUrl;

    private final RestTemplate restTemplate;

    public PaymentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProductResponse> getUserProducts(Long userId) {
        log.info("Интеграция с client-products-service: получение всех продуктов пользователя с ID='{}'", userId);
        ResponseEntity<List<ProductResponse>> entity = restTemplate.exchange(
                productsUrl + "/users/{userId}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                userId
        );
        List<ProductResponse> response = entity.getBody();
        if (!response.isEmpty()) {
            log.info("Интеграция с client-products-service: найдены продукты для пользователя с ID='{}'. Список продуктов :'{}'", userId, response);
        }
        return response;
    }

    @Override
    public ProductResponse getUserProduct(Long userId, Long productId) {
        log.info("Интеграция с client-products-service: получение продукта productId='{}' у пользователя с ID='{}'", productId, userId);
        ResponseEntity<ProductResponse> entity = restTemplate.getForEntity(
                productsUrl + "/{productId}",
                ProductResponse.class,
                productId);
        ProductResponse response = entity.getBody();
        log.info("Интеграция с client-products-service: найден продукт '{}' для пользователя с ID='{}'", response, userId);
        return response;
    }

    @Override
    public ExecuteResponse executePayment(ExecuteRequest request) {
        if (request.productId() == null || request.userId() == null) {
            throw new InvalidRequestException("ID продукта или пользователя не указан");
        }
        log.info("Интеграция с client-products-service: проведение платежа для пользователя с ID='{}'", request.userId());
        ResponseEntity<ExecuteResponse> entity = restTemplate.postForEntity(
                productsUrl + "/payment/execute",
                request,
                ExecuteResponse.class);
        ExecuteResponse response = entity.getBody();
        log.info("Интеграция с client-products-service: платёж для пользователя с ID='{}' проведен успешно. '{}'", request.userId(), response);
        return response;
    }

}
