/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.core.api.QueryParamValue;
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
	public static final String PATH_IDENTIFY = "/identify";

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

	public enum IdentifyType {
		ONEDBARCODE("1dbarcode"),
		QRCODE("qrcode"),
		OBJPIC("objpic"),
		DATAMATRIX("datamatrix"),
		UNKNOWN("unknown");

		private final String queryParam;

		public final static String TYPE_QUERY_PARAM_KEY = "type";

		IdentifyType(final String queryParam) {

			this.queryParam = queryParam;
		}

		public QueryParamValue getQueryParam() {

			return new QueryParamValue(TYPE_QUERY_PARAM_KEY, this.queryParam);
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
	 * POST {@value #PATH_IDENTIFY}
	 * <p>
	 * Scan a Base64 image. Return the corresponding binding in
	 *
	 * @param base64Image base64 encoded image, with mime type header.
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> identifyCreator(final String base64Image) throws EvrythngException {
		return identifyCreator(base64Image, IdentifyType.UNKNOWN);
	}

	/**
	 * POST {@value #PATH_IDENTIFY}
	 * <p>
	 * Identify a Base64 image. Return the corresponding bindings
	 *
	 * @param base64Image base64 encoded image, with mime type header.
	 * @param type   one available {@link IdentifyType}
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> identifyCreator(final String base64Image, final IdentifyType type) throws EvrythngException {
		UrlBinding imageInBinding = new UrlBinding();
		imageInBinding.setImage(base64Image);
		return identifyCreator(imageInBinding, type);
	}

	/**
	 * POST {@value #PATH_IDENTIFY}
	 * <p>
	 * Identify an image. Return the corresponding bindings
	 *
	 * @param image Image as an input stream.
	 * @param mime mime type
	 * @param type   one available {@link IdentifyType}
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> identifyCreator(final InputStream image, final String mime, final IdentifyType type) throws EvrythngException, IOException {
		String mimeAndB64 = encodeBase64(image, mime);
		return identifyCreator(mimeAndB64, type);
	}

	/**
	 * POST {@value #PATH_IDENTIFY}
	 * <p>
	 * Identify an image. Return the corresponding bindings
	 *
	 * @param imageInBinding image {@link UrlBinding} instance
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> identifyCreator(final UrlBinding imageInBinding) throws EvrythngException {
		return identifyCreator(imageInBinding, IdentifyType.UNKNOWN);
	}

	/**
	 * POST {@value #PATH_IDENTIFY}
	 * <p>
	 * Identify an image. Return the corresponding bindings
	 *
	 * @param imageInBinding image {@link UrlBinding} instance
	 * @param type   one available {@link IdentifyType}
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> identifyCreator(final UrlBinding imageInBinding, final IdentifyType type) throws EvrythngException {
		Builder<UrlBinding> result = post(PATH_IDENTIFY, imageInBinding, Status.OK, new TypeReference<UrlBinding>() {

		});
		result.queryParam(type.getQueryParam());
		return result;
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
