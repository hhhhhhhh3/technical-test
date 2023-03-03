package com.hh3.pruebainditex.price.service;

import com.hh3.pruebainditex.price.dto.PriceDTO;
import com.hh3.pruebainditex.price.dto.PriceFilterDTO;

public interface PriceService {
    PriceDTO getPriceByDateAndBrandIdAndProductId(PriceFilterDTO priceFilter);
}
