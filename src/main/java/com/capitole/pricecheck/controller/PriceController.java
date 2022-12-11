package com.capitole.pricecheck.controller;

import com.capitole.pricecheck.domain.Price;
import com.capitole.pricecheck.dto.PriceMapper;
import com.capitole.pricecheck.dto.PriceResponse;
import com.capitole.pricecheck.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;

    }

    @GetMapping("/brands/{brandId}/products/{productId}")
    public ResponseEntity<?> getPriceByApplicationDate(@PathVariable("brandId") Integer brandId, @PathVariable("productId") Long productId, @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        Price price = priceService.getPriceByApplicationDate(brandId, productId, applicationDate);
        PriceResponse priceResponse = PriceMapper.INSTANCE.priceToPriceResponse(price);
        return ResponseEntity.ok(priceResponse);
    }
}

