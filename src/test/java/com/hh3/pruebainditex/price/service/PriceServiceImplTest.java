package com.hh3.pruebainditex.price.service;

import com.hh3.pruebainditex.price.exception.PriceNoDataFoundException;
import com.hh3.pruebainditex.price.dto.PriceDTO;
import com.hh3.pruebainditex.price.model.Price;
import com.hh3.pruebainditex.price.dto.PriceFilterDTO;
import com.hh3.pruebainditex.price.repository.PriceRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private PriceRepository  priceRepository;
    @InjectMocks
    private PriceServiceImpl priceService;

    private Optional<Price> price;
    private PriceFilterDTO priceFilterDTO;

    private LocalDateTime localDateTime;
    private int brandId;
    private int productId;

    @BeforeAll()
    public void setUp() {
        localDateTime = LocalDateTime.parse("2020-06-14T10:00:00");
        brandId = 1;
        productId = 35455;
        priceFilterDTO = new PriceFilterDTO(localDateTime, brandId, productId);
        this.price = Optional.of(new Price(1L,
                                           1,
                                           LocalDateTime.parse("2020-06-14T00:00:00"),
                                           LocalDateTime.parse("2020-12-31T23:59:59"),
                                           1,
                                           35455,
                                           0,
                                           35.50,
                                           "EUR"));
    }


    @Test
    void getPriceByDateAndBrandIdAndProductIdShouldRetrieveAPrice() {
        given(priceRepository.getPriceByDateAndBrandIdAndProductId(localDateTime, brandId, productId)).willReturn(price);
        PriceDTO priceDTORetrieved = priceService.getPriceByDateAndBrandIdAndProductId(priceFilterDTO);
        then(assertThat(priceDTORetrieved).isNotNull());
        assertThat(priceDTORetrieved.price()).isEqualTo(price.get().getPrice());
    }

    @Test
    void getPriceByDateAndBrandIdAndProductIdShouldRetrieveNotFoundException() {
        Assertions.assertThrows(PriceNoDataFoundException.class, () ->
                priceService.getPriceByDateAndBrandIdAndProductId(priceFilterDTO));
        verify(priceRepository, times(1)).getPriceByDateAndBrandIdAndProductId(localDateTime, brandId, productId);
    }
}