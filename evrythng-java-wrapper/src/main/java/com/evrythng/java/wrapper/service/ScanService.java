/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Olten
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.thng.resource.model.li.UrlBinding;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 **/
public class ScanService extends EvrythngServiceBase {

	public static final String PATH_SCAN = "/scan";
	public static final String PATH_SCAN_RECOGNIZE = PATH_SCAN + "/recognitions";
	public static final String PATH_SCAN_BARCODE = PATH_SCAN + "/barcode";

	public enum ScanMethod {

		QRCODE("qrcode"),
		ONEDBARCODE("1dbarcode"),
		OBJPIC("objpic");
		private final String queryParam;

		ScanMethod(final String queryParam) {

			this.queryParam = queryParam;
		}

		public String getQueryParam() {

			return this.queryParam;
		}
	}

	/**
	 * @param api {@link ApiManager} instance
	 */
	public ScanService(final ApiManager api) {

		super(api);
	}

	/**
	 * POST {@value #PATH_SCAN_RECOGNIZE}
	 * <p>
	 * Scan a Base64 image. Return the corresponding binding in
	 *
	 * @param imageInBinding image {@link UrlBinding} instance
	 * @param methods        array of {@link ScanMethod}
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> recognitionsCreator(final UrlBinding imageInBinding, final ScanMethod... methods) throws EvrythngException {

		Builder<UrlBinding> result = post(PATH_SCAN_RECOGNIZE, imageInBinding, Status.OK, new TypeReference<UrlBinding>() {

		});
		for (ScanMethod m : methods) {
			result.queryParam(m.getQueryParam(), "true");
		}
		return result;
	}

	/**
	 * POST {@value #PATH_SCAN_RECOGNIZE}
	 *
	 * @param base64Image base64 encoded image, with mime type header.
	 * @param methods     array of {@link ScanMethod}
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> recognitionsCreator(final String base64Image, final ScanMethod... methods) throws EvrythngException {

		UrlBinding imageInBinding = new UrlBinding();
		imageInBinding.setImage(base64Image);
		return recognitionsCreator(imageInBinding, methods);
	}

	/**
	 * POST {@value #PATH_SCAN_RECOGNIZE}
	 *
	 * @param image   image input stream. The input stream is quietly closed after
	 *                call of this method.
	 * @param mime    MIME type
	 * @param methods array of {@link ScanMethod}
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> recognitionsCreator(final InputStream image, final String mime, final ScanMethod... methods) throws EvrythngException, IOException {

		String mimeAndB64 = encodeBase64(image, mime);
		return recognitionsCreator(mimeAndB64, methods);
	}

	/**
	 * POST {@value #PATH_SCAN_BARCODE}
	 * <p>
	 * Scan a barcode. Return the corresponding binding in
	 *
	 * @param scanBarcodeData bar code {@link UrlBinding}
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> scanBarcodeCreator(final UrlBinding scanBarcodeData) throws EvrythngException {

		return post(PATH_SCAN_BARCODE, scanBarcodeData, Status.OK, new TypeReference<UrlBinding>() {

		});
	}
}
