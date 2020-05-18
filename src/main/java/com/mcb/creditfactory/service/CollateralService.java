package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.Assessment;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class CollateralService {

//    @Autowired
    ApplicationContext context;

    ICollateralObjectService service;

//    @Autowired
    AssessmentService assessmentService;

    public CollateralService(@Autowired AssessmentService assessmentService, @Autowired ApplicationContext context) {
        this.assessmentService = assessmentService;
        this.context = context;
    }

    @SuppressWarnings("ConstantConditions")
    public Long saveCollateral(Collateral object) {
        chooseService(object);
        boolean approved = service.approve(object);
        if (!approved) {
            return null;
        }

        return Optional.of(object)
                .map(service::fromDto)
                .map(service::save)
                .map(el -> {
                    assessmentService.save(el.getId(), el.getType(), el.getValue());
                    return el;
                })
                .map(service::getId)
                .orElse(null);
    }

    public Collateral getInfo(Collateral object) {
        chooseService(object);
        return Optional.of(object)
                .map(service::fromDto)
                .map(service::getId)
                .flatMap(service::load)
                .flatMap(el -> {
                    Assessment ass = assessmentService.loadByIdAndType(el.getType(), el.getId());
                    el.setValue(ass.getValue());
                    return Optional.of(el);
                })
                .map(service::toDTO)
                .orElse(null);
    }

    private void chooseService(Collateral object) {
        if (object instanceof CarDto) {
            service = context.getBean(CarService.class);
        }
        else if (object instanceof AirplaneDto) {
            service = context.getBean(AirplaneService.class);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

}
