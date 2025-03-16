package org.ippnat.repository;


import org.ippnat.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByUserId(Long userId);

    Optional<Product> findByIdAndUser_Id(Long id, Long userId);

}
