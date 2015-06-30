/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.Map;

public class BatchPopulatingTask extends TaskOnBatch {

	private static final long serialVersionUID = 2514640549756857875L;
	// product in here
	private BatchPopulatingTaskResult result;
	public static final String FIELD_RESULT = "result";
	private InputParameters inputParameters;
	public static final String FIELD_INPUT_PARAMETERS = "inputParameters";

	// Might be promoted to a super class !
	public static enum Status {
		PENDING, EXECUTING, TERMINATED, CANCELING
	}

	private Status status;
	public static final String FIELD_STATUS = "status";

	public InputParameters getInputParameters() {

		return inputParameters;
	}

	public void setInputParameters(final InputParameters inputParameters) {

		this.inputParameters = inputParameters;
	}

	public Status getStatus() {

		return status;
	}

	public void setStatus(final Status status) {

		this.status = status;
	}

	public BatchPopulatingTaskResult getResult() {

		return result;
	}

	public void setResult(final BatchPopulatingTaskResult result) {

		this.result = result;
	}

	public static interface InputParameters {

		String FIELD_TYPE = "type";
		public static final String FIELD_SHORT_DOMAIN = "shortDomain";
		public static final String FIELD_GENERATE_THNGS = "generateThngs";
		public static final String FIELD_GENERATE_REDIRECTIONS = "generateRedirections";
		public static final String FIELD_DEFAULT_REDIRECT_URL = "defaultRedirectUrl";
		public static final String FIELD_THNG_TEMPLATE = "thngTemplate";

		Type getType();

		void setType(final Type type);

		public static enum Type {

			FIXED_AMOUNT, FILE_BASED, LIST_BASED
		}
		
		Boolean getGenerateThngs();
		Boolean getGenerateRedirections();
		String getShortDomain();
		String getDefaultRedirectUrl();
		ThngTemplate getThngTemplate();
	}

	public static final class BatchPopulatingTaskResult extends BaseTaskResult {
		private Long totalCount;
		private String location;
		private FileBasedAdiInputParameters.Format format;
		private Map<String, String> headers;

		public Long getTotalCount() {

			return totalCount;
		}

		public void setTotalCount(final Long totalCount) {

			this.totalCount = totalCount;
		}

		public String getLocation() {

			return location;
		}

		public void setLocation(final String location) {

			this.location = location;
		}

		public FileBasedAdiInputParameters.Format getFormat() {

			return format;
		}

		public void setFormat(final FileBasedAdiInputParameters.Format format) {

			this.format = format;
		}

		public Map<String, String> getHeaders() {

			return headers;
		}

		public void setHeaders(final Map<String, String> headers) {

			this.headers = headers;
		}
	}
}
