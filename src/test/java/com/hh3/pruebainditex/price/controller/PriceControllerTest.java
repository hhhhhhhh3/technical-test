package com.hh3.pruebainditex.price.controller;

import com.hh3.pruebainditex.exception.PriceNoDataFoundException;
import com.hh3.pruebainditex.price.dto.PriceDTO;
import com.hh3.pruebainditex.price.dto.PriceFilterDTO;
import com.hh3.pruebainditex.price.service.PriceServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PriceController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PriceControllerTest {

    @Autowired
    private MockMvc          mockMvc;
    @MockBean
    private PriceServiceImpl priceService;

    private PriceDTO       priceDTO;
    private PriceFilterDTO priceFilterDTO;

    @BeforeAll
    public void setUp() {
        LocalDateTime localDateTime = LocalDateTime.parse("2020-06-14T10:00:00");
        int           brandId       = 1;
        int           productId     = 35455;
        priceFilterDTO = new PriceFilterDTO(localDateTime, brandId, productId);
        priceDTO       = new PriceDTO(1,
                                      35455,
                                      1,
                                      LocalDateTime.parse("2020-06-14T00:00:00"),
                                      LocalDateTime.parse("2020-12-31T23:59:59"),
                                      35.50,
                                      "EUR");
    }

    @DisplayName("Get Price By Date, brandId and productId should return filtered price")
    @Test
    void getPriceByDateAndBrandIdAndProductIdShouldReturnFilteredPrice() throws Exception {
        given(priceService.getPriceByDateAndBrandIdAndProductId(priceFilterDTO)).willReturn(priceDTO);
        ResultActions response = mockMvc.perform(get("/price?date={date}&brandId={brandId}&productId={productId}",
                                                     priceFilterDTO.date(),
                                                     priceFilterDTO.brandId(),
                                                     priceFilterDTO.productId()));
        then(response.andExpect(status().isOk())
                     .andExpect(jsonPath("$.priceList", is(priceDTO.priceList())))
                     .andExpect(jsonPath("$.productId", is(priceDTO.productId())))
                     .andExpect(jsonPath("$.brandId", is(priceDTO.brandId())))
                     .andDo(print()));
    }

    @DisplayName("Get Price By Date, brandId and productId should return PriceNoDataFoundException")
    @Test
    void getPriceByDateAndBrandIdAndProductIdShouldReturnNoDataFoundException() throws Exception {
        given(priceService.getPriceByDateAndBrandIdAndProductId(priceFilterDTO)).willThrow(new PriceNoDataFoundException(
                "no prices found for these filter"));
        ResultActions response = mockMvc.perform(get("/price?date={date}&brandId={brandId}&productId={productId}",
                                                     priceFilterDTO.date(),
                                                     priceFilterDTO.brandId(),
                                                     priceFilterDTO.productId()));
        then(response.andExpect(status().isNotFound()).andDo(print()));
    }

    @DisplayName("Get Price By Date, brandId and productId with bad formatted params, should return Bad Request Exception")
    @Test
    void getPriceByDateAndBrandIdAndProductIdWhenBadFormattedParamShouldReturnBadRequest() throws Exception {
        ResultActions response = mockMvc.perform(get("/price?date={date}&brandId={brandId}&productId={productId}",
                                                     priceFilterDTO.date(),
                                                     "typeMismatch",
                                                     "typeMismatch"));
        then(response.andExpect(status().isBadRequest()).andDo(print()));
    }

    @DisplayName("Get Price By Date, brandId and productId without a param, should return Bad Request Exception")
    @Test
    void getPriceByDateAndBrandIdAndProductIWhenMissedParamShouldReturnBadRequest() throws Exception {
        ResultActions response = mockMvc.perform(get("/price"));
        then(response.andExpect(status().isBadRequest()).andDo(print()));
    }
}