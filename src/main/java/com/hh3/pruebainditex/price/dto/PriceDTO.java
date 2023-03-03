package com.hh3.pruebainditex.price.dto;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record PriceDTO(int brandId, int productId, int priceList, LocalDateTime startDate, LocalDateTime endDate,
                       double price, String currency) {
}
