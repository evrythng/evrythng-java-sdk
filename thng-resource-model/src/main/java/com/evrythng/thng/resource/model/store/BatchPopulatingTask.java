/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

public class BatchPopulatingTask extends TaskOnBatch {

	private static final long serialVersionUID = 2514640549756857875L;
	// product in here
	private Result result;
	private InputParameters inputParameters;

	public InputParameters getInputParameters() {

		return inputParameters;
	}

	public void setInputParameters(final InputParameters inputParameters) {

		this.inputParameters = inputParameters;
	}

	public static interface InputParameters {

		String FIELD_TYPE = "type";

		Type getType();

		void setType(final Type type);

		public static enum Type {

			FIXED_AMOUNT, FILE_BASED
		}
		
		Boolean getGenerateThngs();
		Boolean getGenerateRedirections();
	}
	
	public static final class Result {
		
		// TODO implement hierarchy with type
	} 
}
