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
	public static final String FIELD_INPUT_PARAMETERS = "inputParameters";


	public InputParameters getInputParameters() {

		return inputParameters;
	}

	public void setInputParameters(final InputParameters inputParameters) {

		this.inputParameters = inputParameters;
	}

	public static interface InputParameters {

		String FIELD_TYPE = "type";
		public final static String FIELD_SHORT_DOMAIN = "shortDomain";
		public final static String FIELD_GENERATE_THNGS = "generateThngs";
		public final static String FIELD_GENERATE_REDIRECTIONS = "generateRedirections";
		public final static String FIELD_DEFAULT_REDIRECT_URL = "defaultRedirectUrl";
		public final static String FIELD_THNG_TEMPLATE = "thngTemplate";

		Type getType();

		void setType(final Type type);

		public static enum Type {

			FIXED_AMOUNT, FILE_BASED
		}
		
		Boolean getGenerateThngs();
		Boolean getGenerateRedirections();
		String getShortDomain();
		String getDefaultRedirectUrl();
		ThngTemplate getThngTemplate();
	}
	
	public static final class Result {
		
		// TODO implement hierarchy with type
	} 
}
