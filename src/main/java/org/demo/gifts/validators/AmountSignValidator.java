package org.demo.gifts.validators;

import org.demo.gifts.constraints.AmountSignConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AmountSignValidator implements ConstraintValidator<AmountSignConstraint, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && value >= 0;
    }
}
