package com.hh3.pruebainditex.price.service;

import com.hh3.pruebainditex.exception.PriceNoDataFoundException;
import com.hh3.pruebainditex.price.dto.PriceDTO;
import com.hh3.pruebainditex.price.dto.PriceFilterDTO;
import com.hh3.pruebainditex.price.model.Price;
import com.hh3.pruebainditex.price.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {
    private PriceRepository priceRepository;

    public PriceDTO getPriceByDateAndBrandIdAndProductId(PriceFilterDTO priceFilter) {
        Optional<Price> price = this.priceRepository.getPriceByDateAndBrandIdAndProductId(priceFilter.date(),
                                                                                          priceFilter.brandId(),
                                                                                          priceFilter.productId());
        if (price.isEmpty()) {
            throw new PriceNoDataFoundException("no prices found with these filters");
        }
        return PriceDTO.builder()
                       .productId(price.get().getProductId())
                       .brandId(price.get().getBrandId())
                       .priceList(price.get().getPriceList())
                       .startDate(price.get().getStartDate())
                       .endDate(price.get().getEndDate()).price(price.get().getPrice())
                       .currency(price.get().getCurrency())
                       .build();

    }
}
