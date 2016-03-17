/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.jobs;

import java.util.List;

public interface WithOptions<OPTION_TYPE extends Enum<OPTION_TYPE>> {

	List<JobOption<OPTION_TYPE>> getOptions();

	void setOptions(List<JobOption<OPTION_TYPE>> options);

	List<OPTION_TYPE> availableOptionTypes();
}
