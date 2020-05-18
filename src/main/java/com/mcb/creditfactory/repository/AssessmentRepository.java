package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.Assessment;
import org.springframework.data.repository.CrudRepository;


public interface AssessmentRepository extends CrudRepository<Assessment, Long> {
    Assessment save(Assessment assessment);
    Assessment findTopByCollateralTypeAndCollateralIdOrderByLocalDateDesc (String collateralType, Long collateralId);
}
