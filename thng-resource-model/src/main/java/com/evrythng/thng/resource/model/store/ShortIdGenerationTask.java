/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

import java.util.Map;

public class ShortIdGenerationTask extends TaskOnBatch {

	private InputParameters inputParameters;
	public static final String FIELD_INPUT_PARAMETERS = "inputParameters";

	public ShortIdGenerationTask() {
		setType(Type.SHORT_ID_GENERATION);
	}

	public InputParameters getInputParameters() {

		return inputParameters;
	}

	public void setInputParameters(final InputParameters inputParameters) {

		this.inputParameters = inputParameters;
	}

	public static class InputParameters {

		private Long quantity;
		public static final String FIELD_QUANTITY = "quantity";

		private ShortIdTemplate shortIdTemplate;
		public static final String FIELD_SHORT_ID_TEMPLATE = "shortIdTemplate";

		public Long getQuantity() {

			return quantity;
		}

		public void setQuantity(final Long quantity) {

			this.quantity = quantity;
		}

		public ShortIdTemplate getShortIdTemplate() {

			return shortIdTemplate;
		}

		public void setShortIdTemplate(final ShortIdTemplate shortIdTemplate) {

			this.shortIdTemplate = shortIdTemplate;
		}
	}

	public static final class Result extends BaseTaskResult {

		private String location;
		private Map<String, String> headers;

        public Result() {

            setType(Type.SHORT_ID_GENERATION);
        }

		public String getLocation() {

			return location;
		}

		public void setLocation(final String location) {

			this.location = location;
		}

		public Map<String, String> getHeaders() {

			return headers;
		}

		public void setHeaders(final Map<String, String> headers) {

			this.headers = headers;
		}
	}
}
