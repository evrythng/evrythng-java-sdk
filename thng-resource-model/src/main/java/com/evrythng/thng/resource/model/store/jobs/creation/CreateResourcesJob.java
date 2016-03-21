/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs.creation;

public interface CreateResourcesJob<RESOURCE> {

	String FIELD_TEMPLATE = "template";
	void setTemplate(RESOURCE template);
	RESOURCE getTemplate();

	String FIELD_INPUT = "input";
	void setInput(Input input);
	Input getInput();

	interface Input {

		enum Type {
			FILE_BASED, LIST_BASED
		}

		String FIELD_TYPE = "type";
		void setType(Type type);
		Type getType();
	}
}
