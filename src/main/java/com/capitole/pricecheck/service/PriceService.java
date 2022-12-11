package com.capitole.pricecheck.service;

import com.capitole.pricecheck.domain.Price;
import com.capitole.pricecheck.exception.PriceNotFoundException;
import com.capitole.pricecheck.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getPriceByApplicationDate(Integer brandId, Long productId, LocalDateTime applicationDate) {

        Price priceFound = priceRepository.getPriceByApplicationDate(brandId, productId, applicationDate)
                .orElseThrow(() -> new PriceNotFoundException("Price for Product with ID: " + productId + ", Brand with ID: " + brandId + ", and Application Date: " + applicationDate + " not found"));
        return priceFound;
    }
}
