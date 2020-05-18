package com.mcb.creditfactory;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.CollateralService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTaskApplicationTests {

    @Autowired
    private CollateralService collateralService;

    @Test
    public void saveAndLoad() {
        AirplaneDto airplane = new AirplaneDto("Yakovlev", "MS-21", "IAZ", (short) 2000, 1000, 200, new BigDecimal(240_000_000));
        Long saveRes = collateralService.saveCollateral(airplane);
        Collateral airplaneLoaded = collateralService.getInfo(new AirplaneDto(1L));
        System.out.println(airplaneLoaded);
        assertEquals(1, (long) saveRes);
        assertNotNull(airplaneLoaded);
        assertTrue(airplaneLoaded instanceof AirplaneDto);
        assertNotNull(((AirplaneDto) airplaneLoaded).getId());
        assertNotNull(((AirplaneDto) airplaneLoaded).getValue());

        CarDto car = new CarDto("testBrand", "LoModel", 20.0, (short) 2015, new BigDecimal(2_000_000));
        saveRes = collateralService.saveCollateral(car);
        Collateral carLoaded = collateralService.getInfo(new CarDto(1L));
        System.out.println(carLoaded);
        assertEquals(1, (long) saveRes);
        assertNotNull(carLoaded);
        assertTrue(carLoaded instanceof CarDto);
        assertNotNull(((CarDto) carLoaded).getId());
        assertNotNull(((CarDto) carLoaded).getValue());
    }

}
