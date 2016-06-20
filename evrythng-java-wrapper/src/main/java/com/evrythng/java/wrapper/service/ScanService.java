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
	public static final String PATH_SCAN_RECOGNITIONS = PATH_SCAN + "/recognitions";
	public static final String PATH_SCAN_BARCODE = PATH_SCAN + "/barcode";

	public enum RecognitionType {
		ONEDBARCODE("1dbarcode"),
		QRCODE("qrcode"),
		OBJPIC("objpic"),
		DATAMATRIX("datamatrix"),
		AUTODETECT("autodetect");

		private final String queryParam;

		public final static String TYPE_QUERY_PARAM_KEY = "type";

		RecognitionType(final String queryParam) {

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
	 * POST {@value #PATH_SCAN_RECOGNITIONS}
	 * <p>
	 * Scan a Base64 image. Return the corresponding binding in
	 *
	 * @param base64Image base64 encoded image, with mime type header.
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> recognitionCreator(final String base64Image) throws EvrythngException {
		return recognitionCreator(base64Image, RecognitionType.AUTODETECT);
	}

	/**
	 * POST {@value #PATH_SCAN_RECOGNITIONS}
	 * <p>
	 * Identify a Base64 image. Return the corresponding bindings
	 *
	 * @param base64Image base64 encoded image, with mime type header.
	 * @param type   one available {@link RecognitionType}
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> recognitionCreator(final String base64Image, final RecognitionType type) throws EvrythngException {
		UrlBinding imageInBinding = new UrlBinding();
		imageInBinding.setImage(base64Image);
		return recognitionCreator(imageInBinding, type);
	}

	/**
	 * POST {@value #PATH_SCAN_RECOGNITIONS}
	 * <p>
	 * Identify an image. Return the corresponding bindings
	 *
	 * @param image Image as an input stream.
	 * @param mime mime type
	 * @param type   one available {@link RecognitionType}
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> recognitionCreator(final InputStream image, final String mime, final RecognitionType type) throws EvrythngException, IOException {
		String mimeAndB64 = encodeBase64(image, mime);
		return recognitionCreator(mimeAndB64, type);
	}

	/**
	 * POST {@value #PATH_SCAN_RECOGNITIONS}
	 * <p>
	 * Identify an image. Return the corresponding bindings
	 *
	 * @param imageInBinding image {@link UrlBinding} instance
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> recognitionCreator(final UrlBinding imageInBinding) throws EvrythngException {
		return recognitionCreator(imageInBinding, RecognitionType.AUTODETECT);
	}

	/**
	 * POST {@value #PATH_SCAN_RECOGNITIONS}
	 * <p>
	 * Identify an image. Return the corresponding bindings
	 *
	 * @param imageInBinding image {@link UrlBinding} instance
	 * @param type   one available {@link RecognitionType}
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<UrlBinding> recognitionCreator(final UrlBinding imageInBinding, final RecognitionType type) throws EvrythngException {
		Builder<UrlBinding> result = post(PATH_SCAN_RECOGNITIONS, imageInBinding, Status.OK, new TypeReference<UrlBinding>() {

		});
		result.queryParam(type.getQueryParam());
		return result;
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
