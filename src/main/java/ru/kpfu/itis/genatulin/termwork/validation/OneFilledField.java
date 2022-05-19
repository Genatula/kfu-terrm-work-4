package ru.kpfu.itis.genatulin.termwork.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OneFilledFieldValidator.class)
public @interface OneFilledField {
    String message() default "At least one field must be filled in";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
