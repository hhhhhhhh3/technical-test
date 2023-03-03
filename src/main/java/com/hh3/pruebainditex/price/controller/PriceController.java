package com.hh3.pruebainditex.price.controller;


import com.hh3.pruebainditex.price.dto.PriceDTO;
import com.hh3.pruebainditex.price.dto.PriceFilterDTO;
import com.hh3.pruebainditex.price.service.PriceServiceImpl;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/price")
@AllArgsConstructor
public final class PriceController {

    private final PriceServiceImpl priceService;

    @GetMapping
    @Description("get price by date, brandId and productId")
    public ResponseEntity<PriceDTO> getPriceByDateAndBrandIdAndProductId(PriceFilterDTO priceFilter) {
        return ResponseEntity.ok(priceService.getPriceByDateAndBrandIdAndProductId(priceFilter));
    }
}
