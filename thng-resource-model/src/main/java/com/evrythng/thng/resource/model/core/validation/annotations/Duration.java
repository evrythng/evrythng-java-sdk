/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.core.validation.annotations;

import com.evrythng.thng.resource.model.core.DurationModel;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = Duration.Validator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@WithStandaloneMessage
public @interface Duration {

	// TODO _MS_ externalize
	String message() default "endsAt must be strictly greater than startsAt";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	public static class Validator implements ConstraintValidator<Duration, DurationModel> {

		private static final Log log = LoggerFactory.make();

		@Override
		public void initialize(final Duration parameters) {

		}

		@Override
		public boolean isValid(final DurationModel value, final ConstraintValidatorContext constraintValidatorContext) {

			if (value == null || value.getStartsAt() == null || value.getEndsAt() == null) {
				return true;
			} else {
				long start = value.getStartsAt();
				long end = value.getEndsAt();
				return start < end;
			}
		}
	}
}
