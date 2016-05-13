/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.examples;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.ExampleRunner;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.service.ThngService;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.evrythng.thng.resource.model.store.Thng;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Usage example of the EVRYTHNG Java Wrapper for accessing the <a
 * href="https://dashboard.evrythng.com/developers/apidoc#thngs">Thngs</a> API.
 * </p>
 * 
 * <p>
 * In this example, you will learn how to:
 * </p>
 * 
 * <ul>
 * <li>Initialize the {@link ApiManager}</li>
 * <li>Retrieve the {@link ThngService} through the {@link ApiManager}</li>
 * <li>Create some {@link Thng} resources</li>
 * <li>Count the total number of {@link Thng} resources</li>
 * <li>Navigate through pages of {@link Thng} resources</li>
 * <li>Update a {@link Thng} resource</li>
 * <li>Delete a {@link Thng} resource</li>
 * </ul>
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ThngApiExample extends ExampleRunner {

	public ThngApiExample(final ApiConfiguration config) {
		super(config);
	}

	public static void main(final String[] args) throws EvrythngException {

		if (args.length <= 1) {
			usage();
			return;
		}

		ApiConfiguration config = extractConfig(args);

		// Run example:
		new ThngApiExample(config).run();

		System.exit(0);
	}

	/* {@inheritDoc}
	 * @see com.evrythng.api.wrapper.examples.ExampleRunner#doRun()
	 */
	@Override
	protected void doRun() throws EvrythngException {
		// Initialize the API Manager:
		echo("Initializing the ApiManager: [config={}]", getConfig());
		ApiManager apiManager = new ApiManager(getConfig());

		// Let's create some Thng resources using the ThngService:
		echo("Retrieving the Thng API service...");
		ThngService thngService = apiManager.thngService();

		echo("Creating 5 Thng resources...");
		List<Thng> thngs = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			// Build data for a new Thng:
			Thng data = new Thng();
			data.setName("Thng " + i);
			data.setDescription("Description for Thng " + i);
			data.addCustomFields("position", String.valueOf(i));
			thngs.add(data);

			// Retrieve a thngCreator builder and execute it:
			echo("Creating Thng {}: [input={}]", i, data);
			Thng thng = thngService.thngCreator(data).execute();
			echo("Thng {} created: [output={}]", i, thng);
		}

		// Get a Thngs reader builder for querying resources:
		echo("Getting a reader for Thng resources...");
		Builder<List<Thng>> thngsReader = thngService.thngsReader();

		echo("Counting total number of Thng resources...");
		echo("Total: {}", thngsReader.count());

		// Navigate through resource pages using a thngsReader builder:
		echo("Reading first page of Thng resources...");
		List<Thng> results = thngsReader.execute();
		echo("Results: {}", results);

		echo("Reading second page of Thng resources...");
		results = thngsReader.page(2).execute();
		echo("Results: {}", results);

		echo("Reading second page of Thng resources with 2 elements per page...");
		results = thngsReader.page(2).perPage(2).execute();
		echo("Results: {}", results);

		echo("Reading second page of Thng resources with 1 element per page...");
		results = thngsReader.perPage(1).execute(); // "page=2" is still active!
		echo("Results: {}", results);

		// Retrieve a specific Thng using a thngReader builder:
		echo("Retrieving Thng by ID: {}", thngs.get(0).getId());
		Thng retrieved = thngService.thngReader(thngs.get(0).getId()).execute();
		echo("Thng retrieved: {}", retrieved);

		// Update a specific Thng using a thngUpdater builder:
		Thng update = new Thng();
		update.setDescription("I've been updated!");
		echo("Updating Thng: [id={}, update={}]", retrieved.getId(), update);
		Thng updated = thngService.thngUpdater(retrieved.getId(), update).execute();
		echo("Thng updated: {}", updated);

		// Delete a specific Thng using a thngDeleter builder:
		echo("Deleting Thng: [id={}]", retrieved.getId());
		boolean deleted = thngService.thngDeleter(retrieved.getId()).execute();
		echo("Thng deleted: [output={}]", deleted);
	}
}
