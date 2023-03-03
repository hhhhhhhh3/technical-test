package com.hh3.pruebainditex.price.ObjectMother;

import com.hh3.pruebainditex.price.model.Price;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class MotherPrice {
    public static List<Price> testCases() {
        List<Price> listPrices = new ArrayList<>();
        listPrices.add(new Price(1L,
                                 1,
                                 LocalDateTime.parse("2020-06-14T00:00:00"),
                                 LocalDateTime.parse("2020-12-31T23:59:59"),
                                 1,
                                 35455,
                                 0,
                                 35.50,
                                 "EUR"));
        listPrices.add(new Price(2L,
                                 1,
                                 LocalDateTime.parse("2020-06-14T15:00:00"),
                                 LocalDateTime.parse("2020-06-14T18:30:00"),
                                 2,
                                 35455,
                                 1,
                                 25.45,
                                 "EUR"));
        listPrices.add(new Price(3L,
                                 1,
                                 LocalDateTime.parse("2020-06-15T00:00:00"),
                                 LocalDateTime.parse("2020-06-15T11:00:00"),
                                 3,
                                 35455,
                                 1,
                                 30.50,
                                 "EUR"));
        listPrices.add(new Price(4L,
                                 1,
                                 LocalDateTime.parse("2020-06-15T16:00:00"),
                                 LocalDateTime.parse("2020-12-31T23:59:59"),
                                 4,
                                 35455,
                                 1,
                                 38.95,
                                 "EUR"));
        return listPrices;
    }
}
