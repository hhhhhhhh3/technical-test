package com.hh3.pruebainditex.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.Map;

@OpenAPIDefinition
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI api() {
        Components components = new Components();
        Schema priceFilteredResponse = new Schema<Map<String, Object>>().addProperty("brandId",
                                                                                     new StringSchema().example("1"))
                                                                        .addProperty("productId",
                                                                                     new StringSchema().example("35455"))
                                                                        .addProperty("priceList",
                                                                                     new StringSchema().example("1"))
                                                                        .addProperty("startDate",
                                                                                     new StringSchema().example(
                                                                                             "2020-06-14T00:00:00"))
                                                                        .addProperty("endDate",
                                                                                     new StringSchema().example(
                                                                                             "2020-06-14T00:00:00"))
                                                                        .addProperty("price",
                                                                                     new StringSchema().example("20.00"))
                                                                        .addProperty("currency",
                                                                                     new StringSchema().example("EUR"));

        Schema priceFilter = new Schema<Map<String, Object>>().addProperty("date",
                                                                                    new StringSchema().example("2020-06-14T00:00:00"))
                                                                       .addProperty("brandId",
                                                                                    new StringSchema().example("1"))
                                                                       .addProperty("productId",
                                                                                    new StringSchema().example("35455"));

        components.addSchemas("priceFilteredResponse", priceFilteredResponse);
        components.addSchemas("priceFilter", priceFilter);


        ApiResponse OKApi = new ApiResponse().content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                                                                                 new io.swagger.v3.oas.models.media.MediaType().schema(
                                                                                         priceFilteredResponse)));
        ApiResponse badRequestApi = new ApiResponse().content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                                                                                         new io.swagger.v3.oas.models.media.MediaType().addExamples(
                                                                                                 "default",
                                                                                                 new Example().value(
                                                                                                         "{ \"timestamp\" : \"2023-03-03T19:17:59.921701599\", \"Status\" : \"400\", \"error\" : \"BAD REQUEST\", \"errors\" : \"Failed to convert value of type 'java.lang.String' to required type 'java.time.LocalDateTime'; Failed to convert from type [java.lang.String] to type...'\"}"))));

        ApiResponse notFoundApi = new ApiResponse().content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                                                                                       new io.swagger.v3.oas.models.media.MediaType().addExamples(
                                                                                               "default",
                                                                                               new Example().value(
                                                                                                       "{\"timestamp\" : \"2023-03-03T19:17:59.921701599\", \"Status\" : \"404\", \"error\" : \"Not Found\", \"Message\" :\"no prices found with these filters\"}"))));
        components.addResponses("badRequest", badRequestApi);
        components.addResponses("notFound", notFoundApi);
        components.addResponses("OK", OKApi);

        return new OpenAPI().components(components)
                            .info(new Info().title("Prueba Ignacio")
                                            .version("1.0.0")
                                            .description("Prueba tecnica Ignacio Rubio"));
    }
}
