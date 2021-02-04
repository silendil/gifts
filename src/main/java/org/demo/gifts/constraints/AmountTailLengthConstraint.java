package org.demo.gifts.constraints;

import org.demo.gifts.validators.AmountTailLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = AmountTailLengthValidator.class)
public @interface AmountTailLengthConstraint {
    String message() default "{constraint.tooLong}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
