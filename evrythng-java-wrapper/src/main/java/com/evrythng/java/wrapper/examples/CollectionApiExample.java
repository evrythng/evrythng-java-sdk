/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.examples;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.ExampleRunner;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.service.CollectionService;
import com.evrythng.java.wrapper.service.ThngService;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.evrythng.thng.resource.model.store.Collection;
import com.evrythng.thng.resource.model.store.Thng;

import java.util.ArrayList;
import java.util.List;

/**
 * Usage example of the EVRYTHNG Java Wrapper for accessing the <a
 * href="https://dashboard.evrythng.com/developers/apidoc#collections">Collection</a> API. In this example, you will learn how to:
 * <ul>
 * <li>Initialize the {@link ApiManager}</li>
 * <li>Retrieve the {@link CollectionService} through the {@link ApiManager}</li>
 * <li>Create some {@link Collection} resources</li>
 * <li>Count the total number of {@link Collection} resources</li>
 * <li>Navigate through pages of {@link Collection} resources</li>
 * <li>Update a {@link Collection} resource</li>
 * <li>Add some existing {@link Thng} resources to a specific {@link Collection}</li>
 * <li>Delete a {@link Collection} resource</li>
 * </ul>
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class CollectionApiExample extends ExampleRunner {

	public CollectionApiExample(final ApiConfiguration config) {
		super(config);
	}

	public static void main(final String[] args) throws EvrythngException {

		if (args.length <= 1) {
			usage();
			return;
		}

		ApiConfiguration config = extractConfig(args);

		// Run example:
		new CollectionApiExample(config).run();

		System.exit(0);
	}

	/* {@inheritDoc}
	 * @see com.evrythng.api.wrapper.examples.ExampleRunner#doRun()
	 */
	@Override
	protected void doRun() throws EvrythngException {
		// We initialize the API Manager:
		echo("Initializing the ApiManager: [config={}]", getConfig());
		ApiManager apiManager = new ApiManager(getConfig());

		// Let's create some Collection resources using the CollectionService:
		echo("Retrieving the Collection API service...");
		CollectionService collectionService = apiManager.collectionService();

		echo("Creating 5 Collection resources...");
		List<Collection> collections = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			// Build data for a new Collection:
			Collection data = new Collection();
			data.setName("Collection " + i);
			data.setDescription("Description for Collection " + i);
			data.addCustomFields("index", String.valueOf(i));

			// Retrieve a collectionCreator builder and execute it:
			echo("Creating Collection {}: [input={}]", i, data);
			Collection collection = collectionService.collectionCreator(data).execute();
			echo("Collection {} created: [output={}]", i, collection);

			collections.add(collection);
		}

		// Get a Collections reader builder for querying resources:
		echo("Getting a reader for Collection resources...");
		Builder<List<Collection>> collectionsReader = collectionService.collectionsReader();

		echo("Counting total number of Collection resources...");
		echo("Total: {}", collectionsReader.count());

		// Navigate through resource pages using a collectionsReader builder:
		echo("Reading first page of Collection resources...");
		List<Collection> results = collectionsReader.execute();
		echo("Results: {}", results);

		echo("Reading second page of Collection resources...");
		results = collectionsReader.page(2).execute();
		echo("Results: {}", results);

		echo("Reading second page of Collection resources with 2 elements per page...");
		results = collectionsReader.page(2).perPage(2).execute();
		echo("Results: {}", results);

		echo("Reading second page of Collection resources with 1 element per page...");
		results = collectionsReader.perPage(1).execute(); // "page=2" is still active
		echo("Results: {}", results);

		// Retrieve a specific Collection using a collectionReader builder:
		echo("Retrieving Collection by ID: {}", collections.get(0).getId());
		Collection retrieved = collectionService.collectionReader(collections.get(0).getId()).execute();
		echo("Collection retrieved: {}", retrieved);

		// Update a specific Collection using a collectionUpdater builder:
		Collection update = new Collection();
		update.setDescription("I've been updated!");
		echo("Updating Collection: [id={}, update={}]", retrieved.getId(), update);
		Collection updated = collectionService.collectionUpdater(retrieved.getId(), update).execute();
		echo("Collection updated: {}", updated);

		// Create some Thng resources and the Thng references
		// to a specific Collection using a thngsAdder builder:
		List<String> thngReferences = createThngReferences(apiManager, 3);
		echo("Adding Thng resources to Collection: [id={}, input={}]", retrieved.getId(), thngReferences);
		collectionService.thngsAdder(retrieved.getId(), thngReferences).execute();

		// Delete a specific Collection using a collectionDeleter builder:
		echo("Deleting Collection: [id={}]", retrieved.getId());
		boolean deleted = collectionService.collectionDeleter(retrieved.getId()).execute();
		echo("Collection deleted: [output={}]", deleted);
	}

	/**
	 * Creates {@link Thng} resources and return their references.
	 * 
	 * @param apiManager the configured {@link ApiManager}
	 * @param count the number of {@link Thng} resources to create
	 * @return a {@link List} of {@link Thng} references (ID)
	 * @throws EvrythngException
	 * @throws EvrythngClientException
	 */
	protected List<String> createThngReferences(final ApiManager apiManager, final int count) throws EvrythngException {
		echo("Creating {} Thng resources...", count);
		ThngService thngService = apiManager.thngService();
		List<String> references = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			// Build data for a new Thng:
			Thng data = new Thng();
			data.setName("Thng " + i);
			data.setDescription("Description for Thng " + i);
			data.addCustomFields("position", String.valueOf(i));

			// Retrieve a thngCreator builder and execute it:
			echo("Creating Thng {}: [input={}]", i, data);
			Thng thng = thngService.thngCreator(data).execute();
			echo("Thng {} created: [output={}]", i, thng);

			// Store Thng reference:
			references.add(thng.getId());
		}
		return references;
	}
}
