package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.IModelObject;
import java.util.Optional;

public interface ICollateralObjectService{
    boolean approve(Collateral dto);
    IModelObject save(IModelObject collateral);
    Optional<? extends IModelObject> load(Long id);
    IModelObject fromDto(Collateral dto);
    Collateral toDTO(IModelObject collateral);
    Long getId(IModelObject collateral);
}
