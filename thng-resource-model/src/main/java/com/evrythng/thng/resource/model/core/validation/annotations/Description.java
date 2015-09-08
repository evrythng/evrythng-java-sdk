/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.core.validation.annotations;

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

@Constraint(validatedBy = Description.Validator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

	int min() default 2;

	int max() default 300;

	// TODO _MS_ externalize
	String message() default "length must be between {min} and {max}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	public static class Validator implements ConstraintValidator<Description, CharSequence> {

		private static final Log log = LoggerFactory.make();
		private int min;
		private int max;

		@Override
		public void initialize(final Description parameters) {

			this.min = parameters.min();
			this.max = parameters.max();
			this.validateParameters();
		}

		@Override
		public boolean isValid(final CharSequence value, final ConstraintValidatorContext constraintValidatorContext) {

			if (value == null) {
				return true;
			} else {
				int length = value.length();
				return length >= this.min && length <= this.max;
			}
		}

		private void validateParameters() {

			if (this.min < 0) {
				throw log.getMinCannotBeNegativeException();
			} else if (this.max < 0) {
				throw log.getMaxCannotBeNegativeException();
			} else if (this.max < this.min) {
				throw log.getLengthCannotBeNegativeException();
			}
		}
	}
}
