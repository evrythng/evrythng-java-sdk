/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs;

import java.util.List;

public interface WithStatusHistory {

	Status getStatus();

	List<NewStatus> getHistory();

	void setHistory(List<NewStatus> history);

	void addHistory(NewStatus... history);
}
