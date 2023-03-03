package com.hh3.pruebainditex.price.integration;

import com.hh3.pruebainditex.price.ObjectMother.MotherPrice;
import com.hh3.pruebainditex.price.dto.PriceFilterDTO;
import com.hh3.pruebainditex.price.repository.PriceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PriceControllerITest {

    @Autowired
    private MockMvc          mockMvc;
    @Autowired
    private PriceRepository priceRepository;

    private PriceFilterDTO priceFilterDTO;

    @BeforeAll
    public void setUp() {
        priceRepository.saveAll(MotherPrice.testCases());
        LocalDateTime localDateTime = LocalDateTime.parse("2020-06-14T10:00:00");
        int brandId        = 1;
        int productId      = 35455;
        priceFilterDTO = new PriceFilterDTO(localDateTime, brandId, productId);

    }

    @DisplayName("Get Price By Date, brandId and productId should return filtered price")
    @Test
    void getPriceByDateAndBrandIdAndProductIdShouldReturnFilteredPrice() throws Exception {
        ResultActions response = mockMvc.perform(get("/price?date={date}&brandId={brandId}&productId={productId}",
                                                     priceFilterDTO.date(),
                                                     priceFilterDTO.brandId(),
                                                     priceFilterDTO.productId()));
        then(response.andExpect(status().isOk())
                     .andExpect(jsonPath("$.priceList", is(1)))
                     .andExpect(jsonPath("$.productId", is(priceFilterDTO.productId())))
                     .andExpect(jsonPath("$.brandId", is(priceFilterDTO.brandId())))
                     .andDo(print()));
    }

    @DisplayName("Get Price By Date, brandId and productId should return PriceNoDataFoundException")
    @Test
    void getPriceByDateAndBrandIdAndProductIdShouldReturnPriceNoDataFoundException() throws Exception {
        ResultActions response = mockMvc.perform(get("/price?date={date}&brandId={brandId}&productId={productId}",
                                                     priceFilterDTO.date(),
                                                     priceFilterDTO.brandId(),
                                                     23));
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
