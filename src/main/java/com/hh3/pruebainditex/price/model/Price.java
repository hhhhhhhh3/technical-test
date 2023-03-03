package com.hh3.pruebainditex.price.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity(name = "prices")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class Price {
    @Id
    private Long          priceId;
    private int           brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int           priceList;
    private int           productId;
    private int           priority;
    private double        price;
    private String        currency;
}
