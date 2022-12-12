package com.capitole.pricecheck.repository;

import com.capitole.pricecheck.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> getPriceByApplicationDate(Integer brandId, Long productId, LocalDateTime applicationDate);
}

