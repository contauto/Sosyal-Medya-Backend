package com.example.learningSpring.user;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueUsernameValidator.class })
public @interface UniqueUsername {

        String message() default "{learningSpring.Username.UniqueUsername.Message}";

        Class<?>[] groups() default { };

        Class<? extends Payload>[] payload() default { };
}