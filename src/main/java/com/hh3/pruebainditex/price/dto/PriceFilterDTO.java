package com.hh3.pruebainditex.price.dto;

import java.time.LocalDateTime;

public record PriceFilterDTO(LocalDateTime date, int brandId, int productId) {
}
