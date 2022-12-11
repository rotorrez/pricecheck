package com.capitole.pricecheck.dto;

import com.capitole.pricecheck.domain.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(target = "productId", source = "price.productId")
    @Mapping(target = "brandId", source = "price.brandId")
    @Mapping(target = "priceList", source = "price.priceList")
    @Mapping(target = "startDate", source = "price.startDate")
    @Mapping(target = "endDate", source = "price.endDate")
    @Mapping(target = "price", source = "price.price")
    PriceResponse priceToPriceResponse(Price price);
}
