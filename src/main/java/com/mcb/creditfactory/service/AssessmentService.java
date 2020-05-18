package com.mcb.creditfactory.service;

import com.mcb.creditfactory.model.Assessment;
import com.mcb.creditfactory.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class AssessmentService {

    AssessmentRepository assessmentRepository;

    @Autowired
    public AssessmentService(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    public Assessment loadByIdAndType(String collateralType, Long collateralId) {
        return assessmentRepository.findTopByCollateralTypeAndCollateralIdOrderByLocalDateDesc(collateralType, collateralId);
    }

    public Assessment save(Long collateralId, String collateralType,BigDecimal value) {
        return assessmentRepository.save(new Assessment(collateralId, collateralType, value));
    }

}
