/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.core.api.param.EvrythngIdQueryParamValue;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.thng.resource.model.li.UrlBinding;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;

/**
 * Service wrapper for the {@code /redirections} endpoint.
 **/
@SuppressWarnings("AnonymousInnerClassMayBeStatic")
public class UrlBindingService extends EvrythngServiceBase {

	public static final String PATH_URLS = "/redirections";
	public static final String PATH_URL_BINDING = PATH_URLS + "/%s";
	public static final String PATH_URL_BINDING_QR = PATH_URLS + "/%s.qr";

	/**
	 * @param api {@link ApiManager} instance
	 */
	public UrlBindingService(final ApiManager api) {

		super(api);
	}

	/**
	 * Creates url binding.
	 *
	 * @param binding {@link UrlBinding} instance.
	 *
	 * @return a preconfigured {@link Builder}.
	 */
	public Builder<UrlBinding> bindingCreator(final UrlBinding binding) throws EvrythngException {

		return post(PATH_URLS, binding, new TypeReference<UrlBinding>() {

		});
	}

	/**
	 * Reads url bindings.
	 *
	 * @return a preconfigured {@link Builder}.
	 */
	public Builder<List<UrlBinding>> bindingsReader() throws EvrythngException {

		return get(PATH_URLS, new TypeReference<List<UrlBinding>>() {

		});
	}

	/**
	 * Creates url binding. Qr image is returned.
	 *
	 * @param binding {@link UrlBinding} instance.
	 *
	 * @return a preconfigured {@link Builder}.
	 */
	public Builder<InputStream> bindingCreatorReturnQr(final UrlBinding binding) throws EvrythngException {

		return post(PATH_URLS, binding, Status.OK, new TypeReference<InputStream>() {

		}).accept("image/png");
	}

	/**
	 * Reads url bindings mapped to evrythng id provided.
	 *
	 * @param evrythngId id of evrythng resource.
	 *
	 * @return a preconfigured {@link Builder}.
	 */
	public Builder<List<UrlBinding>> bindingsReader(final String evrythngId) throws EvrythngException {

		return super.get(PATH_URLS, new TypeReference<List<UrlBinding>>() {

		}).queryParam(EvrythngIdQueryParamValue.id(evrythngId));
	}

	/**
	 * Reads url binding by short id.
	 *
	 * @param shortId short id.
	 *
	 * @return a preconfigured {@link Builder}.
	 */
	public Builder<UrlBinding> bindingReader(final String shortId) throws EvrythngException {

		return get(String.format(PATH_URL_BINDING, shortId), new TypeReference<UrlBinding>() {

		});
	}

	/**
	 * Updates url binding.
	 *
	 * @param shortId short id.
	 * @param binding {@link UrlBinding} instance.
	 *
	 * @return a preconfigured {@link Builder}.
	 */
	public Builder<UrlBinding> bindingUpdater(final String shortId, final UrlBinding binding) throws EvrythngException {

		return put(String.format(PATH_URL_BINDING, shortId), binding, new TypeReference<UrlBinding>() {

		});
	}

	/**
	 * Deletes url binding.
	 *
	 * @param shortId short id
	 *
	 * @return a preconfigured {@link Builder}.
	 */
	public Builder<Boolean> bindingDeleter(final String shortId) throws EvrythngClientException {

		return delete(String.format(PATH_URL_BINDING, shortId));
	}

	/**
	 * Reads qr code.
	 *
	 * @param shortId short id.
	 * @param type    {@link QrCodeType} type.
	 *
	 * @return a preconfigured {@link Builder}.
	 */
	public Builder<InputStream> bindingQrCodeReader(final String shortId, final QrCodeType type) throws EvrythngException {

		String accept;
		switch (type) {
			case PDF:
				accept = "application/pdf";
				break;
			case SVG:
				accept = "image/svg+xml";
				break;
			case PNG:
			default:
				accept = "image/png";
				break;
		}
		return get(String.format(PATH_URL_BINDING_QR, shortId), new TypeReference<InputStream>() {

		}).accept(accept);
	}

}
