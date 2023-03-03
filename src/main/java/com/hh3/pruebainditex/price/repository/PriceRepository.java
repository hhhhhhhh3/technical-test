package com.hh3.pruebainditex.price.repository;

import com.hh3.pruebainditex.price.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM prices p where ?1 BETWEEN  p.startDate AND p.endDate and p.brandId = ?2 and p.productId = ?3 order by p.priority DESC LIMIT 1")
    Optional<Price> getPriceByDateAndBrandIdAndProductId(LocalDateTime date, int brandId, int productId);
}