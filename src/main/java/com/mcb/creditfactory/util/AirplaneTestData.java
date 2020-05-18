package com.mcb.creditfactory.util;

import com.mcb.creditfactory.model.Airplane;

import java.math.BigDecimal;

public class AirplaneTestData {

    public static final Airplane AIRPLANE1 = new Airplane(null, "newBrand", "Boing-777", "BoingCo", (short)1000, 327,  66, new BigDecimal(56_000_000));

    public static Airplane getNew() {
        return new Airplane(null, "newBrand", "Boing-777", "BoingCo", (short)1000, 327, 66, new BigDecimal(56_000_000));
    }
}