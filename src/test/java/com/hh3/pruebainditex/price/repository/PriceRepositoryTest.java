package com.hh3.pruebainditex.price.repository;

import com.hh3.pruebainditex.price.model.Price;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    private int           brandId;
    private int           productId;
    private LocalDateTime localDateTime;
    private Price         priceFound;

    @BeforeAll
    public void setUp() {
        this.brandId   = 1;
        this.productId = 35455;
        priceRepository.save(new Price(1L,
                                       1,
                                       LocalDateTime.parse("2020-06-14T00:00:00"),
                                       LocalDateTime.parse("2020-12-31T23:59:59"),
                                       1,
                                       35455,
                                       0,
                                       35.50,
                                       "EUR"));
        priceRepository.save(new Price(2L,
                                       1,
                                       LocalDateTime.parse("2020-06-14T15:00:00"),
                                       LocalDateTime.parse("2020-06-14T18:30:00"),
                                       2,
                                       35455,
                                       1,
                                       25.45,
                                       "EUR"));
        priceRepository.save(new Price(3L,
                                       1,
                                       LocalDateTime.parse("2020-06-15T00:00:00"),
                                       LocalDateTime.parse("2020-06-15T11:00:00"),
                                       3,
                                       35455,
                                       1,
                                       30.50,
                                       "EUR"));
        priceRepository.save(new Price(4L,
                                       1,
                                       LocalDateTime.parse("2020-06-15T16:00:00"),
                                       LocalDateTime.parse("2020-12-31T23:59:59"),
                                       4,
                                       35455,
                                       1,
                                       38.95,
                                       "EUR"));
    }

    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void getPriceByDateAndBrandIdAndProductIdCase1() {
        //given
        localDateTime = LocalDateTime.parse("2020-06-14T10:00:00");
        //when
        Price priceFound = priceRepository.getPriceByDateAndBrandIdAndProductId(localDateTime, brandId, productId)
                                          .get();
        //then
        Assertions.assertThat(priceFound.getPriceList()).isEqualTo(1);
    }

    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void getPriceByDateAndBrandIdAndProductIdCase2() {
        //given
        localDateTime = LocalDateTime.parse("2020-06-14T16:00:00");
        //when
        priceFound = priceRepository.getPriceByDateAndBrandIdAndProductId(localDateTime, brandId, productId)
                                    .get();
        //then
        Assertions.assertThat(priceFound.getPriceList()).isEqualTo(2);
    }

    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void getPriceByDateAndBrandIdAndProductIdCase3() {
        //given
        localDateTime = LocalDateTime.parse("2020-06-14T21:00:00");
        //when
        priceFound = priceRepository.getPriceByDateAndBrandIdAndProductId(localDateTime, brandId, productId)
                                    .get();
        //then
        Assertions.assertThat(priceFound.getPriceList()).isEqualTo(1);
    }

    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void getPriceByDateAndBrandIdAndProductIdCase4() {
        //given
        localDateTime = LocalDateTime.parse("2020-06-15T10:00:00");
        //when
        priceFound = priceRepository.getPriceByDateAndBrandIdAndProductId(localDateTime, brandId, productId)
                                    .get();
        //then
        Assertions.assertThat(priceFound.getPriceList()).isEqualTo(3);
    }

    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void getPriceByDateAndBrandIdAndProductIdCase5() {
        //given
        localDateTime = LocalDateTime.parse("2020-06-16T21:00:00");
        //when
        priceFound = priceRepository.getPriceByDateAndBrandIdAndProductId(localDateTime, brandId, productId)
                                    .get();
        //then
        Assertions.assertThat(priceFound.getPriceList()).isEqualTo(4);
    }
}