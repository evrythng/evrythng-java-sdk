/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class BatchPopulatingTask extends TaskOnBatch {

	private static final long serialVersionUID = 2514640549756857875L;
	// product in here
	private BatchPopulatingTaskResult result;
	public static final String FIELD_RESULT = "result";
	private InputParameters inputParameters;
	public static final String FIELD_INPUT_PARAMETERS = "inputParameters";
	private Progress progress;
	public static final String FIELD_PROGRESS = "progress";

	// Might be promoted to a super class !
	public static enum Status {
		PENDING, EXECUTING, EXECUTED, CANCELING
	}

	public static final class Progress {

		private Map<String, Integer> thngs;
		private Map<String, Integer> urlBindings;
		private Integer totalAmount;

		public Progress() {

			thngs = new HashMap<>();
			urlBindings = new HashMap<>();
		}

		public void addCreatedThngsCount(final String taskId, final int count) {

			thngs.merge(taskId, count, new BiFunction<Integer, Integer, Integer>() {

				@Override
				public Integer apply(final Integer one, final Integer another) {

					return one + another;
				}
			});
		}

		public void addCreatedUrlBindingsCount(final String taskId, final int count) {

			urlBindings.merge(taskId, count, new BiFunction<Integer, Integer, Integer>() {

				@Override
				public Integer apply(final Integer one, final Integer another) {

					return one + another;
				}

				;
			});
		}

		public Integer getThngsCount() {

			return thngs.values().stream().collect(Collectors.summingInt(new ToIntFunction<Integer>() {

				@Override
				public int applyAsInt(final Integer value) {

					return value;
				}
			}));
		}

		public Integer getUrlBindingsCount() {

			return urlBindings.values().stream().collect(Collectors.summingInt(new ToIntFunction<Integer>() {

				@Override
				public int applyAsInt(final Integer value) {

					return value;
				}
			}));
		}

		public Integer getTotalAmount() {

			return totalAmount;
		}

		public void setTotalAmount(final Integer totalAmount) {

			this.totalAmount = totalAmount;
		}
		
		public boolean isComplete(){
			
			return getThngsCount() >= totalAmount && getUrlBindingsCount() >= totalAmount;
		}

		public static final class Contribution {

			private String contributor;
			private int urlBindings;
			private int thngs;

			public Contribution(final String contributor) {

				this.contributor = contributor;
			}

			public void addCreatedThngsCount(final int count) {

				thngs += count;
			}

			public void addCreatedUrlBindingsCount(final Integer count) {

				urlBindings += count;
			}

			public Integer getThngsCount() {

				return thngs;
			}

			public Integer getUrlBindingsCount() {

				return urlBindings;
			}
		}
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

	public Progress getProgress() {

		return progress;
	}

	public void setProgress(final Progress progress) {

		this.progress = progress;
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
