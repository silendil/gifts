package org.demo.gifts.constraints;

import org.demo.gifts.validators.AmountSignValidator;
import org.demo.gifts.validators.CurrencyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = CurrencyValidator.class)
public @interface CurrencyConstraint {
    String message() default "{constraint.currencyNotAcceptable}}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
