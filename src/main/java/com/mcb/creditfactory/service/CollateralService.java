package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.Assessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;



@Service
public class CollateralService {

    ApplicationContext context;

    @Autowired
    Map<String, ICollateralObjectService> serviceMap;

    ICollateralObjectService service;

    AssessmentService assessmentService;

    @Autowired
    public CollateralService(AssessmentService assessmentService, ApplicationContext context) {
        this.assessmentService = assessmentService;
        this.context = context;
    }

    @SuppressWarnings("ConstantConditions")
    public Long saveCollateral(Collateral object) {
        service = serviceMap.get(object.getClass().getSimpleName());
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
        service = serviceMap.get(object.getClass().getSimpleName());
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
}
