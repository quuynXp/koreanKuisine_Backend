package com.connectJPA.demo.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Constraint(validatedBy = {})
@Retention(RUNTIME)
public @interface DobContraints {
    String message() default "Invalid date of birth";

    int min();

    Class<?>[] group() default {};

    Class<? extends Payload>[] payload() default {};
}
