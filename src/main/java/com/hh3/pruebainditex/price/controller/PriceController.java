package com.hh3.pruebainditex.price.controller;


import com.hh3.pruebainditex.price.dto.PriceDTO;
import com.hh3.pruebainditex.price.dto.PriceFilterDTO;
import com.hh3.pruebainditex.price.service.PriceServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
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
    @Operation(description = "Get price by Date, brandId and productId", responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(ref = "priceFilteredResponse"))),
                                                                                      @ApiResponse(responseCode = "400", ref = "badRequest"),
                                                                                      @ApiResponse(responseCode = "404", ref = "notFound")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(ref = "priceFilter"))))
    public ResponseEntity<PriceDTO> getPriceByDateAndBrandIdAndProductId(PriceFilterDTO priceFilter) {
        return ResponseEntity.ok(priceService.getPriceByDateAndBrandIdAndProductId(priceFilter));
    }
}
