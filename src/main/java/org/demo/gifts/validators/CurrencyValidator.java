package org.demo.gifts.validators;

import org.demo.gifts.constraints.CurrencyConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<CurrencyConstraint, String> {
    public static final String[] ACCEPTABLE_VALUES = {"EUR", "USD", "BYN"};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String acceptableValue : ACCEPTABLE_VALUES)
            if (acceptableValue.equals(value))
                return true;
        return false;
    }
}
