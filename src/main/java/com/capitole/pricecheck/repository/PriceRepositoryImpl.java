package com.capitole.pricecheck.repository;


import com.capitole.pricecheck.datasource.PriceRepositoryJpa;
import com.capitole.pricecheck.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceRepositoryJpa priceRepositoryJpa;

    @Autowired
    public PriceRepositoryImpl(PriceRepositoryJpa priceRepositoryJpa) {
        this.priceRepositoryJpa = priceRepositoryJpa;
    }


    @Override
    public Optional<Price> getPriceByApplicationDate(Integer brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepositoryJpa.getPriceByApplicationDate(brandId, productId, applicationDate);
    }
}
