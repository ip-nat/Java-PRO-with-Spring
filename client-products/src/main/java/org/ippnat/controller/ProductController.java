package org.ippnat.controller;

import org.ippnat.model.dto.PaymentRequest;
import org.ippnat.model.dto.ProductResponse;
import org.ippnat.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public ProductResponse getProduct(@PathVariable Long productId) {
        return service.getProduct(productId);
    }

    @GetMapping("/users/{userId}")
    public List<ProductResponse> getUserProducts(@PathVariable Long userId) {
        return service.getUserProducts(userId);
    }

    @PostMapping("/payment/execute")
    public ProductResponse executeTransaction(@RequestBody PaymentRequest request) {
        return service.executeTransaction(request);
    }

}
