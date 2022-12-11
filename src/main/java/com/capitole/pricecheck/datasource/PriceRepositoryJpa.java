package com.capitole.pricecheck.datasource;

import com.capitole.pricecheck.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;


public interface PriceRepositoryJpa extends JpaRepository<Price, Long> {

    @Query(value = "SELECT * FROM PRICES WHERE BRAND_ID = :brandId AND PRODUCT_ID = :productId AND :applicationDate BETWEEN START_DATE AND END_DATE ORDER BY PRIORITY DESC LIMIT 1",
            nativeQuery = true)
    Optional<Price> getPriceByApplicationDate(@Param("brandId") Integer brandId, @Param("productId") Long productId, @Param("applicationDate") LocalDateTime applicationDate);
}
