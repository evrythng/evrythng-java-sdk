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
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.StringProperty;
import com.evrythng.thng.resource.model.store.Thng;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Usage example of the EVRYTHNG Java Wrapper for accessing the <a
 * href="https://dashboard.evrythng.com/developers/apidoc#properties">Thngs &gt;
 * Properties</a> API.
 * </p>
 * <p>
 * In this example, you will learn how to:
 * </p>
 * 
 * <ul>
 * <li>Initialize the {@link ApiManager}</li>
 * <li>Retrieve the {@link ThngService} through the {@link ApiManager}</li>
 * <li>Create a {@link Thng} resources</li>
 * <li>Create some Property resources on an existing {@link Thng}</li>
 * <li>Retrieve Property resources from an existing {@link Thng}</li>
 * <li>Retrieve last Property values from an existing {@link Thng}</li>
 * <li>Update the value of a specific Property on an existing {@link Thng}</li>
 * <li>Delete a specific Property on an existing {@link Thng}</li>
 * <li>Retrieve Property values from an existing {@link Thng} using temporal
 * queries</li>
 * </ul>
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ThngPropertyApiExample extends ExampleRunner {

	public ThngPropertyApiExample(final ApiConfiguration config) {
		super(config);
	}

	public static void main(final String[] args) throws EvrythngException {

		if (args.length <= 1) {
			usage();
			return;
		}

		ApiConfiguration config = extractConfig(args);

		// Run example:
		new ThngPropertyApiExample(config).run();

		System.exit(0);
	}

	/*
	 * {@inheritDoc}
	 * 
	 * @see com.evrythng.api.wrapper.examples.ExampleRunner#doRun()
	 */
	@Override
	protected void doRun() throws EvrythngException {
		// Initialize the API Manager:
		echo("Initializing the ApiManager: [config={}]", getConfig());
		ApiManager apiManager = new ApiManager(getConfig());

		// Let's create a Thng resource using the ThngService:
		echo("Retrieving the Thng API service...");
		ThngService thngService = apiManager.thngService();

		// Build data for a new Thng:
		Thng thngData = new Thng();
		thngData.setName("Tissot t-touch");
		thngData.setDescription("This is my favorite watch for hiking!");
		thngData.addCustomFields("color", "black");

		// Retrieve a Thng creator builder and execute it:
		echo("Creating a new Thng: [input={}]", thngData);
		Thng thng = thngService.thngCreator(thngData).execute();
		echo("Thng created: [output={}]", thng);

		// Build some sample data for creating new Property resources:
		List<Property<?>> properties = new ArrayList<>();
		properties.add(new StringProperty("temperature", String.valueOf(Math.random())));
		properties.add(new StringProperty("altitude", String.valueOf(Math.random())));

		// Now, we can create these Property resources on a specific Thng
		// using a propertiesCreator builder:
		echo("Creating new Property resources: [thngId={}, input={}]", thng.getId(), properties);
		List<Property<?>> results = thngService.propertiesCreator(thng.getId(), properties).execute();
		echo("Property resources created: [output={}]", results);

		// Retrieve created Property resources using a propertiesReader builder:
		echo("Retrieving Property resources from Thng: [thngId={}]", thng.getId());
		Builder<List<Property<?>>> thngPropertiesReader = thngService.propertiesReader(thng.getId());
		results = thngPropertiesReader.execute();
		echo("Thng Property resources retrieved: [output={}]", results);

		// Retrieve last values of a specific Property using a propertyReader builder:
		echo("Retrieving last values of the {} Property: [thngId={}]", "temperature", thng.getId());
		Builder<List<Property<?>>> temperatureReader = thngService.propertyReader(thng.getId(), "temperature");
		List<Property<?>> values = temperatureReader.execute();
		echo("List of Property retrieved: [size={}, output={}]", values.size(), values);

		// Update value of a specific Property:
		echo("Updating value of the {} Property: [thngId={}]", "temperature", thng.getId());
		Builder<List<Property<?>>> temperatureUpdater = thngService.propertyUpdater(thng.getId(), "temperature", String.valueOf(Math.random()));
		values = temperatureUpdater.execute();
		echo("Thng Property value updated: [output={}]", values);

		// Delete a Property and all it's values using a propertyDeleter builder:
		echo("Deleting the {} Property: [thngId={}]", "temperature", thng.getId());
		boolean deleted = thngService.propertyDeleter(thng.getId(), "temperature").execute();
		echo("Thng Property deleted: [output={}]", deleted);

		// Try to retrieve deleted Property values using the temperatureReader builder:
		echo("Retrieving values of {} Property: [thngId={}]", "temperature", thng.getId());
		values = temperatureReader.execute();
		echo("List of Property retrieved: [size={}, output={}]", values.size(), values);

		// Update the speed Property in order to perform some temporal queries
		// using a propertyReader:
		thngService.propertyUpdater(thng.getId(), "speed", "10", 1000L).execute();
		thngService.propertyUpdater(thng.getId(), "speed", "20", 2000L).execute();
		thngService.propertyUpdater(thng.getId(), "speed", "30", 3000L).execute();
		thngService.propertyUpdater(thng.getId(), "speed", "40", 4000L).execute();
		Builder<List<Property<?>>> speedReader = thngService.propertyReader(thng.getId(), "speed");

		echo("Retrieving temporal values of {} Property: [thngId={}, from={}]", "speed", thng.getId(), 2000);
		values = speedReader.from(2000).execute();
		echo("List of PropertyValue retrieved: [size={}, output={}]", values.size(), values);

		echo("Retrieving temporal values of {} Property: [thngId={}, to={}]", "speed", thng.getId(), 3000);
		values = speedReader.to(3000).execute(); // from=2000 is still active!
		echo("List of PropertyValue retrieved: [size={}, output={}]", values.size(), values);

		echo("Retrieving temporal values of {} Property: [thngId={}, from={}, to={}]", "speed", thng.getId(), 1000, 4000);
		values = speedReader.from(1000).to(4000).execute();
		echo("List of PropertyValue retrieved: [size={}, output={}]", values.size(), values);
	}
}
