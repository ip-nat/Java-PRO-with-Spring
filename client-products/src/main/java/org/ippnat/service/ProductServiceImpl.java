package org.ippnat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ippnat.entity.Product;
import org.ippnat.model.dto.PaymentRequest;
import org.ippnat.model.dto.ProductResponse;
import org.ippnat.model.exception.InsufficientBalanceException;
import org.ippnat.model.exception.ProductNotFoundException;
import org.ippnat.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse getProduct(Long productId) {
        log.info("Получение продукта по ID='{}'", productId);
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("Продукт с ID=" + productId + " не найден"));

        return new ProductResponse(product.getId(), product.getAccountId(), product.getType(), product.getBalance());
    }

    @Override
    public List<ProductResponse> getUserProducts(Long userId) {
        log.info("Получение всех продуктов пользователя с ID='{}'", userId);
        List<Product> products = productRepository.findAllByUserId(userId);

        if (products.isEmpty()) {
            log.warn("Продукты не найдены для пользователя с ID='{}'", userId);
            return Collections.emptyList();
        }

        log.info("Количество найденных продуктов '{}' :'{}'", products.size(), products);
        return products.stream()
                .map(product -> new ProductResponse(product.getId(), product.getAccountId(), product.getType(), product.getBalance()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponse executeTransaction(PaymentRequest request) {
        log.info("Проведение платежа для пользователя с ID='{}'", request.userId());
        Product product = this.getProductByIdAndUserId(request.productId(), request.userId());
        BigDecimal currentBalance = product.getBalance();

        if (request.amount() == null
                ||Objects.isNull(currentBalance)
                || currentBalance.compareTo(request.amount()) < 0) {
            log.warn("Невозможно провести платеж по продукту '{}'", product.getAccountId());
            throw new InsufficientBalanceException("Недостаточно средств на счете");
        }

        product.setBalance(currentBalance.subtract(request.amount()));
        this.updateProduct(product);
        log.info("Платёж по продукту '{}' для пользователя с ID='{}' проведен успешно.", product.getAccountId(), request.userId());

        return new ProductResponse(
                product.getId(),
                product.getAccountId(),
                product.getType(),
                product.getBalance()
        );
    }

    private Product getProductByIdAndUserId(Long productId, Long userId) {
        log.info("Получение продукта по ID='{}' у пользователя с ID='{}'", productId, userId);
        return productRepository.findByIdAndUser_Id(productId, userId)
                .orElseThrow(() -> new ProductNotFoundException("Продукт или пользователь не найден"));
    }

    private void updateProduct(Product product) {
        log.debug("Обновление продукта '{}'", product);
        productRepository.save(product);
    }

}