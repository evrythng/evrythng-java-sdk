/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.util;

import org.junit.Assert;
import org.junit.Test;

public class LogUtilsTest {

	@Test
	public void maskApiKeyTest() {

		Assert.assertEquals("N708W...5015S", LogUtils.maskApiKey("N708W589120i0Tn984uwX6Z9c8582ccyN42Ah9PG18sO66bOCGf46sann382306D203304s2RowSA628G7KeWx1h2A8657H5015S"));
		Assert.assertEquals("PJGt...ube", LogUtils.maskApiKey("PJGttqarjMhhBYddWFKxwfZEqGERCDJCZHuveHwfUFJgokYfxupwKWyFHDGlcVRDCzgBDnRHeube"));
		Assert.assertEquals("ZZ...2", LogUtils.maskApiKey("ZZA0td8MG3RHW94ILyyz3ZLxzLrU3H02"));
		Assert.assertEquals("j...", LogUtils.maskApiKey("jCo0O0bJqDX4Qmi"));
		Assert.assertEquals("N...", LogUtils.maskApiKey("NAdompnPVE"));
		Assert.assertEquals("...", LogUtils.maskApiKey("m4Mm"));
		Assert.assertEquals("...", LogUtils.maskApiKey(""));
	}
}
