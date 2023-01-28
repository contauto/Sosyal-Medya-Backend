package com.example.learningSpring.sos;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {SosValidator.class})
public @interface AppropriateWord {
    String message() default "{learningSpring.Sos.AppropriateSos.Message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
