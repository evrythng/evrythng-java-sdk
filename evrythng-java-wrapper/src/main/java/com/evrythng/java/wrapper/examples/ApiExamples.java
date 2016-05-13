/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.examples;

import com.evrythng.java.wrapper.core.ExampleRunner;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.thng.commons.config.ApiConfiguration;

/**
 * Runner suite for all EVRYTHNG API examples.
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ApiExamples {

	public static void main(final String[] args) throws EvrythngException {

		if (args.length <= 1) {
			ExampleRunner.usage();
			return;
		}

		ApiConfiguration config = ExampleRunner.extractConfig(args);

		// Run all examples:
		new SimpleExample(config).run();
		new ThngApiExample(config).run();
		new ThngPropertyApiExample(config).run();
		new ExceptionHandlingExample(config).run();

		System.exit(0);
	}
}
