package org.demo.gifts.constraints;

import org.demo.gifts.validators.AmountSignValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = AmountSignValidator.class)
public @interface AmountSignConstraint {

    String message() default "{constraint.notNegative}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
