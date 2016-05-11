package com.evrythng.java.wrapper.examples;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.thng.resource.model.store.BooleanProperty;
import com.evrythng.thng.resource.model.store.NumberProperty;
import com.evrythng.thng.resource.model.store.Product;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.Thng;
import org.pcollections.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class QuickTour {

	public static void main(String[] args) {

		String apiKey = "<your_apiKey>";
		ApiManager client = new ApiManager(apiKey);

		// we define what the Product will look like
		Product productToCreate = new Product();
		productToCreate.setName("Ferrari F40");
		productToCreate.setDescription("A mid-engine, rear-wheel drive, two-door coupé sports car built from 1987 to 1992.");
		productToCreate.addCustomFields("category", "sport");
		productToCreate.addCustomFields("type", "coupé");
		productToCreate.setTags(Arrays.asList("car", "sport", "ferrari", "coupé"));

		// here we actually create it using the EVRYTHNG API, after this line you can call product.getId() to retrieve the id assigned to it.
		Product f40 = client.productService().productCreator(productToCreate).execute();

		System.out.println(f40.getId());

		// our first Thng.
		Thng myRedF40 = new Thng();
		myRedF40.setName("My red Ferrari F40");
		// it's an F40!
		myRedF40.setProduct(f40.getId());
		myRedF40.setDescription("It's red!");
		myRedF40.addCustomFields("color", "red");
		myRedF40.addCustomFields("license_plate", "NY - ABC 2345");
		myRedF40.setTags(Arrays.asList("red", "US", "NY"));

		myRedF40 = client.thngService().thngCreator(myRedF40).execute();

		// and another one.
		Thng myYellowF40 = new Thng();
		myYellowF40.setName("My yellow Ferrari F40");
		// it's an F40!
		myYellowF40.setProduct(f40.getId());
		myYellowF40.setDescription("It's yellow! The original Ferrari color.");
		myYellowF40.addCustomFields("color", "yellow");
		myYellowF40.addCustomFields("license_plate", "NY - ABC 1981");
		myYellowF40.setTags(Arrays.asList("yellow", "NY", "US"));

		myYellowF40 = client.thngService().thngCreator(myYellowF40).execute();

		// define current state.
		List<Property<?>> myRedF40State = new ArrayList<>();
		myRedF40State.add(new NumberProperty("engine_temperature", 72.31));
		myRedF40State.add(new BooleanProperty("started", true));

		// perform the update.
		client.thngService().propertiesCreator(myRedF40.getId(), myRedF40State).execute();

		// define current state.
		List<Property<?>> myYellowF40State = new ArrayList<>();
		myYellowF40State.add(new NumberProperty("engine_temperature", 12.23));
		myYellowF40State.add(new BooleanProperty("started", false));

		// perform the update.
		client.thngService().propertiesCreator(myYellowF40.getId(), myYellowF40State).execute();

		// iterating all Thngs.
		Iterator<PVector<Thng>> allMyThngs = client.thngService().iterator().perPage(10).execute();
		while (allMyThngs.hasNext()) {
			PVector<Thng> page = allMyThngs.next();
			// do something with the page
			for (Thng thng : page) {
				System.out.println("Retrieved Thng with name: " + thng.getName());
			}
		}

		// iterating only red Thngs.
		Iterator<PVector<Thng>> redThngsOnly = client.thngService().iterator().perPage(10).filter("tags=red").execute();
		while (redThngsOnly.hasNext()) {
			PVector<Thng> page = redThngsOnly.next();
			// do something with the page
			for (Thng thng : page) {
				System.out.println("Retrieved Thng with name: " + thng.getName());
			}
		}
	}
}