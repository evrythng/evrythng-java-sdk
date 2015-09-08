/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.core.validation.annotations;

import org.apache.commons.validator.routines.DomainValidator;
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
import java.util.List;

@Constraint(validatedBy = ShortDomains.Validator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ShortDomains {

	int min() default 2;

	int max() default 60;

	String message() default "are invalid.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	public static class Validator implements ConstraintValidator<ShortDomains, List<String>> {

		private static final Log log = LoggerFactory.make();

		@Override
		public void initialize(final ShortDomains parameters) {

		}

		@Override
		public boolean isValid(final List<String> value, final ConstraintValidatorContext constraintValidatorContext) {

			if (value == null) {
				return true;
			} else {
				DomainValidator domainValidator = DomainValidator.getInstance();
				for (String domain : value) {
					if (domain == null || domain.isEmpty() || !domainValidator.isValid(domain)) {
						return false;
					}
				}
				return true;
			}
		}
	}
}
