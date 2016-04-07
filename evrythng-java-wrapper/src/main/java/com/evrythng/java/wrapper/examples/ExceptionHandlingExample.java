/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.examples;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder;
import com.evrythng.java.wrapper.core.ExampleRunner;
import com.evrythng.java.wrapper.exception.BadRequestException;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.exception.ForbiddenException;
import com.evrythng.java.wrapper.exception.NotFoundException;
import com.evrythng.java.wrapper.service.ThngService;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.evrythng.thng.resource.model.store.Thng;

/**
 * <p>
 * Example of API exception handling using the EVRYTHNG Java Wrapper.
 * </p>
 *
 * <p>
 * In this example, you will learn how to:
 * </p>
 *
 * <ul>
 * <li>Initialize the {@link ApiManager}</li>
 * <li>Retrieve the {@link ThngService} through the {@link ApiManager}</li>
 * <li>Handle exceptions when performing incorrect operations</li>
 * </ul>
 */
public class ExceptionHandlingExample extends ExampleRunner {

	public ExceptionHandlingExample(final ApiConfiguration config) {
		super(config);
	}

	public static void main(final String[] args) throws EvrythngException {

		if (args.length <= 1) {
			usage();
			return;
		}

		ApiConfiguration config = extractConfig(args);

		// Run example:
		new ExceptionHandlingExample(config).run();

		System.exit(0);
	}

	/* {@inheritDoc}
	 * @see com.evrythng.api.wrapper.examples.ExampleRunner#doRun()
	 */
	@Override
	protected void doRun() throws EvrythngException {
		// Initialize the API Manager:
		ApiManager apiManager = new ApiManager(getConfig());

		// Get the Thng API service:
		ThngService thngService = apiManager.thngService();

		// Let's try to create a Thng with invalid data:
		Thng thng = new Thng(); // Missing required 'name' field
		EvrythngApiBuilder.Builder<Thng> thngCreator = thngService.thngCreator(thng);
		try {
			echo("Trying to create a new Thng: [input={}]", thng);
			thngCreator.execute();
		} catch (BadRequestException e) {
			echo("Exception catched! [class={}]", e.getClass().getName());
		}

		// Let's create a Thng to test NotFoundException handling:
		thng.setName("foo");
		echo("Creating a new Thng: [input={}]", thng);
		thng = thngService.thngCreator(thng).execute();
		echo("Thng created: [output={}]", thng);

		// Now, delete the Thng:
		echo("Deleting Thng: [id={}]", thng.getId());
		boolean deleted = thngService.thngDeleter(thng.getId()).execute();
		echo("Thng deleted: [output={}]", deleted);

		// Let's try to retrieve deleted Thng:
		EvrythngApiBuilder.Builder<Thng> thngReader = thngService.thngReader(thng.getId());
		try {
			echo("Trying to retrieve deleted Thng: [id={}]", thng.getId());
			thngReader.execute();
		} catch (NotFoundException e) {
			echo("Exception catched! [class={}]", e.getClass().getName());
		}

		// Finally, let's try to read Thng resources using an invalid API key:
		try {
			echo("Trying to read Thng resources: [apiKey={}]", "invalid-api-key");
			thngReader.apiKey("invalid-api-key").execute();
		} catch (ForbiddenException e) {
			echo("Exception catched! [class={}]", e.getClass().getName());
		}
	}
}
