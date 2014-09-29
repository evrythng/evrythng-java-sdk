package com.evrythng.commons;

public final class UdpatedResourcesCount {

	public final long updatedCount;

	public UdpatedResourcesCount(long updatedCount) {
		if (updatedCount < 0) {
			throw new IllegalArgumentException("Negative : " + updatedCount);
		}
		this.updatedCount = updatedCount;
	}
}
