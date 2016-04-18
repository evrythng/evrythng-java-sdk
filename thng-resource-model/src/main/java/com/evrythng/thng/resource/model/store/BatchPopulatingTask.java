/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchPopulatingTask extends TaskOnBatch {

	private static final long serialVersionUID = 2514640549756857875L;
	// product in here
	private InputParameters inputParameters;
	public static final String FIELD_INPUT_PARAMETERS = "inputParameters";
	private OutputParameters outputParameters;
	public static final String FIELD_OUTPUT_PARAMETERS = "outputParameters";
	private Progress progress;
	public static final String FIELD_PROGRESS = "progress";

	public BatchPopulatingTask() {

		setType(Type.POPULATING);
	}

	/**
	 * Container class that holds the sub-tasks of the current task.
	 * <p>
	 * The tasks are held as a map of {@link String} =&gt; {@link Contribution} instances with the sub-task ID as the key.
	 */
	public static final class Progress {

		public static final String FIELD_CONTRIBUTIONS = "contributions";
		private Map<String, Contribution> contributions;
		private Integer totalAmount;

		public Progress() {

			contributions = new HashMap<>();
		}

		@JsonIgnore
		public Integer getThngsCount() {

			int thngs = 0;
			for (Contribution contribution : contributions.values()) {

				thngs += contribution.getThngs();
			}

			return thngs;
		}

		@JsonIgnore
		public Integer getUrlBindingsCount() {

			int urlBindings = 0;
			for (Contribution contribution : contributions.values()) {

				urlBindings += contribution.getUrlBindings();
			}

			return urlBindings;
		}

		@JsonIgnore
		public Integer getFailedThngsCount() {

			int failedThngs = 0;
			for (Contribution contribution : contributions.values()) {

				failedThngs += contribution.getFailedThngs();
			}

			return failedThngs;
		}

		@JsonIgnore
		public Integer getFailedUrlBindingsCount() {

			int failedUrlBindings = 0;
			for (Contribution contribution : contributions.values()) {

				failedUrlBindings += contribution.getFailedUrlBindings();
			}

			return failedUrlBindings;
		}

		public Integer getTotalAmount() {

			return totalAmount;
		}

		@JsonIgnore
		public Integer getTotalAmountIncludingFailures() {

			return getThngsCount() + getUrlBindingsCount() + getFailedOperationsAmount();
		}

		public void setTotalAmount(final Integer totalAmount) {

			this.totalAmount = totalAmount;
		}

		public Map<String, Contribution> getContributions() {

			return contributions;
		}

		public void setContributions(final Map<String, Contribution> contributions) {

			this.contributions = contributions;
		}

		@JsonIgnore
		public boolean isComplete() {

			return getTotalAmountIncludingFailures() >= totalAmount;
		}

		@JsonIgnore
		public int getFailedOperationsAmount() {

			return getFailedThngsCount() + getFailedUrlBindingsCount();
		}

		/**
		 * A {@link Contribution} represents a sub-task of {@link BatchPopulatingTask}.
		 */
		public static final class Contribution {

			public static final String FIELD_URL_BINDINGS = "urlBindings";
			private int urlBindings;
			public static final String FIELD_THNGS = "thngs";
			private int thngs;
			public static final String FIELD_FAILED_URL_BINDINGS = "failedUrlBindings";
			private int failedUrlBindings;
			public static final String FIELD_FAILED_THNGS = "failedThngs";
			private int failedThngs;

			@JsonIgnore
			public void addCreatedThngsCount(final int count) {

				thngs += count;
			}

			@JsonIgnore
			public void addCreatedUrlBindingsCount(final Integer count) {

				urlBindings += count;
			}

			@JsonIgnore
			public void addFailedUrlBindingsCount(final int count) {

				failedUrlBindings += count;
			}

			@JsonIgnore
			public void addFailedThngsCount(final int count) {

				failedThngs += count;
			}

			public Integer getFailedThngs() {

				return failedThngs;
			}

			public Integer getFailedUrlBindings() {

				return failedUrlBindings;
			}

			/**
			 * Alias for {@link #getThngs()}
			 *
			 * @return the <strong>number</strong> of THNGS created by this sub-task
			 *
			 * @see #getThngs()
			 * @deprecated since 1.21
			 */
			@JsonIgnore
			@Deprecated
			public Integer getThngsCount() {

				return thngs;
			}

			/**
			 * Alias for {@link #getUrlBindings()}
			 *
			 * @return the <strong>number</strong> of URL Bindings created by this sub-task
			 *
			 * @see #getUrlBindings()
			 * @deprecated since 1.21
			 */
			@JsonIgnore
			@Deprecated
			public Integer getUrlBindingsCount() {

				return urlBindings;
			}

			/**
			 * @return the <strong>number</strong> of THNGS created by this sub-task
			 */
			public Integer getThngs() {

				return thngs;
			}

			/**
			 * @return the <strong>number</strong> of URL Bindings created by this sub-task
			 */
			public Integer getUrlBindings() {

				return urlBindings;
			}

			/**
			 * Sets the <strong>number</strong> of THNGS created by this sub-task
			 *
			 * @param thngs the <strong>number</strong> of THNGS
			 */
			public void setThngs(final int thngs) {

				this.thngs = thngs;
			}

			/**
			 * Sets the <strong>number</strong> of URL Bindings created by this sub-task
			 *
			 * @param urlBindings the <strong>number</strong> of URL Bindings
			 */
			public void setUrlBindings(final int urlBindings) {

				this.urlBindings = urlBindings;
			}
		}
	}

	public InputParameters getInputParameters() {

		return inputParameters;
	}

	public void setInputParameters(final InputParameters inputParameters) {

		this.inputParameters = inputParameters;
	}

	public Progress getProgress() {

		return progress;
	}

	public void setProgress(final Progress progress) {

		this.progress = progress;
	}

	public OutputParameters getOutputParameters() {

		return outputParameters;
	}

	public void setOutputParameters(final OutputParameters outputParameters) {

		this.outputParameters = outputParameters;
	}

	public interface InputParameters {

		String FIELD_TYPE = "type";
		String FIELD_SHORT_DOMAIN = "shortDomain";
		String FIELD_GENERATE_THNGS = "generateThngs";
		String FIELD_GENERATE_REDIRECTIONS = "generateRedirections";
		String FIELD_DEFAULT_REDIRECT_URL = "defaultRedirectUrl";
		String FIELD_THNG_TEMPLATE = "thngTemplate";

		Type getType();

		void setType(final Type type);

		enum Type {

			FIXED_AMOUNT, FILE_BASED, LIST_BASED
		}

		Boolean getGenerateThngs();

		Boolean getGenerateRedirections();

		String getShortDomain();

		String getDefaultRedirectUrl();

		ThngTemplate getThngTemplate();

		void setShortDomain(String shortDomain);

		void setDefaultRedirectUrl(String defaultRedirectUrl);

		void setThngTemplate(ThngTemplate thngTemplate);
	}

	public interface OutputParameters {

		String FIELD_TYPE = "type";
		String FIELD_COLUMNS = "columns";

		Type getType();

		void setType(final Type type);

		enum Type {

			CSV(Format.CSV);
			private final Format format;

			Type(final Format format) {

				this.format = format;
			}

			public Format getFormat() {

				return format;
			}
		}
		
		enum Format {
			
			CSV("csv", "text/csv");
			private final String extension;
			private final String mimeType;

			Format(final String extension, final String mimeType) {

				this.extension = extension;
				this.mimeType = mimeType;
			}

			public String getExtension() {

				return extension;
			}

			public String getMimeType() {

				return mimeType;
			}
		}

		enum Column {

			SHORT_ID("shortId"), THNG("thng");
			private final String name;

			Column(final String name) {

				this.name = name;
			}

			public String getName() {

				return name;
			}
		}

		List<Column> getColumns();
	}

	public static final class Result extends BaseTaskResult {

		private String location;
		private OutputParameters.Format format;
		private Map<String, String> headers;
		private Integer totalThngsCreated;
		private Integer totalUrlBindingsCreated;

		public Result() {

            setType(Type.POPULATING);
        }

		public Integer getTotalCount() {

			return getTotalThngsCreated() + getTotalUrlBindingsCreated();
		}

		public String getLocation() {

			return location;
		}

		public void setLocation(final String location) {

			this.location = location;
		}

		public OutputParameters.Format getFormat() {

			return format;
		}

		public void setFormat(final OutputParameters.Format format) {

			this.format = format;
		}

		public Map<String, String> getHeaders() {

			return headers;
		}

		public void setHeaders(final Map<String, String> headers) {

			this.headers = headers;
		}

		public void setTotalThngsCreated(final Integer totalThngsCreated) {

			this.totalThngsCreated = totalThngsCreated;
		}

		public Integer getTotalThngsCreated() {

			return totalThngsCreated != null ? totalThngsCreated : 0;
		}

		public void setTotalUrlBindingsCreated(final Integer totalUrlBindingsCreated) {

			this.totalUrlBindingsCreated = totalUrlBindingsCreated;
		}

		public Integer getTotalUrlBindingsCreated() {

			return totalUrlBindingsCreated != null ? totalUrlBindingsCreated : 0;
		}
	}
}
