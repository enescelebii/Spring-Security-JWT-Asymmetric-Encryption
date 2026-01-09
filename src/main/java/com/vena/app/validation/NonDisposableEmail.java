package com.vena.app.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( { ElementType.FIELD, ElementType.PARAMETER }) // where to apply this annotation
@Retention(RetentionPolicy.RUNTIME) // when to apply this annotation
@Constraint(validatedBy = EmailDomainValidator.class) // validator class for custom validation
public @interface NonDisposableEmail {

    // basic logic of the annotation
    String message() default "disposable email address is not allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
