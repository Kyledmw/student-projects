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

import com.crowdfunder.control.validation.ProjectPledgeableValidator;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ProjectPledgeableValidator.class)
@Documented
public @interface ProjectPledgeable {

	String message() default "{custom.project.pledgeable}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
