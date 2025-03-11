package org.ippnat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ippnat.entity.Product;
import org.ippnat.model.dto.ProductResponse;
import org.ippnat.model.exception.ProductNotFoundException;
import org.ippnat.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements CommandLineRunner  {

    private final ProductRepository productRepository;

    public ProductResponse getProduct(Long id) {
        log.info("Получение продукта по ID={}", id);
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Продукт с ID=" + id + " не найден"));

        return new ProductResponse(product.getId(), product.getAccountId(), product.getType(), product.getBalance());
    }

    public List<ProductResponse> getUserProducts(Long userId) {
        log.info("Получение всех продуктов пользователя с ID={}", userId);
        List<Product> products = productRepository.findAllByUserId(userId);

        if (products.isEmpty()) {
            log.warn("Продукты не найдены для пользователя с ID={}", userId);
            return Collections.emptyList();
        }

        log.info("Количество найденных продуктов {} :{}", products.size(), products);
        return products.stream()
                .map(product -> new ProductResponse(product.getId(), product.getAccountId(), product.getType(), product.getBalance()))
                .collect(Collectors.toList());
    }

    @Override
    public void run(String... args) {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            log.warn("Продукты не найдены в базе данных.");
            return;
        }

        Product randomProduct = products.get(new Random().nextInt(products.size()));
        Long randomProductId = randomProduct.getId();
        Long randomUserId = randomProduct.getUserId();

        log.info("По id='{}' найден продукт '{}'", randomProductId, getProduct(randomProductId));
        log.info("Найдены продукты пользователя с id='{}'. Список продуктов: '{}'", randomUserId, getUserProducts(randomUserId).toString());
    }

}