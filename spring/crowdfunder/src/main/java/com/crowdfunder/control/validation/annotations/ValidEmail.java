package com.crowdfunder.control.validation.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.crowdfunder.control.validation.RegistrationEmailValidator;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = RegistrationEmailValidator.class)
@Documented
public @interface ValidEmail {

	String message() default "{custom.user.email.taken}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
