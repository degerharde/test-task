package com.mcb.creditfactory.model;

import com.mcb.creditfactory.external.CollateralType;

import java.math.BigDecimal;

public interface IModelObject {

    Long getId();
    String getType();
    BigDecimal getValue();
    void setValue(BigDecimal value);
}
