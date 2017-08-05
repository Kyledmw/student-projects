package com.voting_app.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 ********************************************************************
 * Custom hibernate validator annotation which uses CheckVotesValidator
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckVotesValidator.class)
@Documented
public @interface CheckVotes {

	String message() default "{ie.kyle.validation.CheckVotes" + ".message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
