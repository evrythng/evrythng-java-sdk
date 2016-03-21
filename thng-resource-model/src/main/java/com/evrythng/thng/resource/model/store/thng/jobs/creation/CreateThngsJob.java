/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.thng.jobs.creation;

import com.evrythng.thng.resource.model.store.jobs.creation.CreateResourcesJob;
import com.evrythng.thng.resource.model.store.thng.jobs.ThngJob;

import java.util.Arrays;
import java.util.List;

public final class CreateThngsJob extends ThngJob<CreateThngsJob.OptionType> implements CreateResourcesJob<ThngCreationBundle> {

	private static final long serialVersionUID = -6679521183634195240L;
	private ThngCreationBundle template;
	private Input input;

	public CreateThngsJob() {

		setType(Type.CREATE_THNGS);
	}

	@Override
	public void setTemplate(final ThngCreationBundle template) {

		this.template = template;
	}

	@Override
	public ThngCreationBundle getTemplate() {

		return template;
	}

	@Override
	public void setInput(final Input input) {

		this.input = input;
	}

	@Override
	public Input getInput() {

		return input;
	}

	public enum OptionType {
		SINGLE_RESOURCE_NOTIFICATION, SINGLE_RESOURCE_REACTIONS
	}

	@Override
	public final List<OptionType> availableOptionTypes() {

		return Arrays.asList(OptionType.values());
	}
}
