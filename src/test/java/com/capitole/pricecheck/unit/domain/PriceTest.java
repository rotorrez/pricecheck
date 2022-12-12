package com.capitole.pricecheck.unit.domain;

import com.capitole.pricecheck.domain.Price;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PriceTest {

    @Test
    public void testPriceCreate() {

        Long productId = 35455L;
        Integer brandId = 1;
        Integer priceList = 1;
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2020-12-31T23:59:59");
        BigDecimal priceProduct = new BigDecimal("35.50");
        String curr = "EUR";
        Integer priority = 1;

        Price price = new Price();
        price.setPrice(priceProduct);
        price.setProductId(productId);
        price.setPriceList(priceList);
        price.setBrandId(brandId);
        price.setStartDate(startDate);
        price.setEndDate(endDate);
        price.setCurr(curr);
        price.setPriority(priority);


        assertEquals(productId, price.getProductId());
        assertEquals(brandId, price.getBrandId());
        assertEquals(priceList, price.getPriceList());
        assertEquals(startDate, price.getStartDate());
        assertEquals(endDate, price.getEndDate());
        assertEquals(priceProduct, price.getPrice());
        assertEquals(curr, price.getCurr());
        assertEquals(priority, price.getPriority());
    }
}
