/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.thng.resource.model.store;

public abstract class TaskOnBatch extends Task {

	private static final long serialVersionUID = 8091207835379916024L;
	private String batch;
	private Type type;
	private Status status;
    private BaseTaskResult result;
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_BATCH = "batch";
    public static final String FIELD_RESULT = "result";

	// Might be promoted to a super class !
	public enum Status {
		PENDING, EXECUTING, EXECUTED, EXECUTED_WITH_FAILURES, CANCELING, FAILED
	}

	public Status getStatus() {

		return status;
	}

	public void setStatus(final Status status) {

		this.status = status;
	}

	public Type getType() {

		return type;
	}

	public void setType(final Type type) {

		this.type = type;
	}

	public String getBatch() {

		return batch;
	}

	public void setBatch(final String batch) {

		this.batch = batch;
	}

    public BaseTaskResult getResult() {

        return result;
    }

    public void setResult(final BaseTaskResult result) {

        this.result = result;
    }

    public enum Type {

		POPULATING, SHORT_ID_GENERATION
    }

	public abstract static class BaseTaskResult {
		
		public static String FIELD_TYPE = "type";
		private Type type;

		public static final String FIELD_FAILED_OPERATIONS_AMOUNT = "failedOperationsAmount";

		private Long failedOperationsAmount = 0L;

		public final void setFailedOperationsAmount(final Long failedOperationsAmount) {

			this.failedOperationsAmount = failedOperationsAmount;
		}

		public final Long getFailedOperationsAmount() {

			return failedOperationsAmount;
		}

		public Type getType() {

			return type;
		}

		public void setType(final Type type) {

			this.type = type;
		}

        public enum Type {

            POPULATING, SHORT_ID_GENERATION, ERROR
        }
	}

    public static class ErrorTaskResult extends BaseTaskResult {

        private String error;

        public ErrorTaskResult() {

            setType(Type.ERROR);
        }

        public String getError() {

            return error;
        }

        public void setError(final String error) {

            this.error = error;
        }
    }
}
