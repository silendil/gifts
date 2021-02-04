package org.demo.gifts.validators;

import org.demo.gifts.constraints.AmountTailLengthConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AmountTailLengthValidator implements ConstraintValidator<AmountTailLengthConstraint, Double> {


    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null)
            return false;
        String[] parts = value.toString().split("\\.");
        return parts.length < 2 || parts[1].length() <= 2;
    }
}
