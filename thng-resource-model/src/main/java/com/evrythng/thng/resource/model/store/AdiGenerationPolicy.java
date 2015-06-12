/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

public class AdiGenerationPolicy {

	private Boolean generateThngs;
	private Boolean generateRedirections;
	private ThngTemplate thngTemplate;
	private InputParameters inputParameters;

	public Boolean getGenerateRedirections() {

		return generateRedirections;
	}

	public void setGenerateRedirections(final Boolean generateRedirections) {

		this.generateRedirections = generateRedirections;
	}

	public Boolean getGenerateThngs() {

		return generateThngs;
	}

	public void setGenerateThngs(final Boolean generateThngs) {

		this.generateThngs = generateThngs;
	}

	public InputParameters getInputParameters() {

		return inputParameters;
	}

	public void setInputParameters(final InputParameters inputParameters) {

		this.inputParameters = inputParameters;
	}

	public ThngTemplate getThngTemplate() {

		return thngTemplate;
	}

	public void setThngTemplate(final ThngTemplate thngTemplate) {

		this.thngTemplate = thngTemplate;
	}

	public static interface InputParameters {

		String FIELD_TYPE = "type";

		Type getType();

		void setType(final Type type);

		public static enum Type {

			FIXED_AMOUNT, FILE_BASED
		}
	}
}