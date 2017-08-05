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

import com.crowdfunder.control.validation.UserCanPledgeValidator;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UserCanPledgeValidator.class)
@Documented
public @interface UserCanPledge {

	String message() default "{custom.user.canPledge}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
