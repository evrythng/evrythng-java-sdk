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
import java.util.Map;

@Constraint(validatedBy = CustomFields.Validator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomFields {

	int min() default 1;

	int max() default 60;

	String message() default "are invalid.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	public static class Validator implements ConstraintValidator<CustomFields, Map<String, Object>> {

		private static final Log log = LoggerFactory.make();
		private int min;
		private int max;

		@Override
		public void initialize(final CustomFields parameters) {

			this.min = parameters.min();
			this.max = parameters.max();
			this.validateParameters();
		}

		@Override
		public boolean isValid(final Map<String, Object> value, final ConstraintValidatorContext constraintValidatorContext) {

			if (value == null) {
				return true;
			} else {
				for (Map.Entry<String, Object> customField : value.entrySet()) {
					if (isInvalid(customField)) {
						return false;
					}
				}
				return true;
			}
		}

		private boolean isInvalid(final Map.Entry<String, Object> customField) {

			String key = customField.getKey();
			Object value = customField.getValue();
			if (key == null || key.trim().length() < min || key.trim().length() > max) {
				return true;
			}
			return false;
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
