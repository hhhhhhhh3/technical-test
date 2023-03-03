package com.hh3.pruebainditex.price.repository;

import com.hh3.pruebainditex.price.ObjectMother.MotherPrice;
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
        priceRepository.saveAll(MotherPrice.testCases());
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