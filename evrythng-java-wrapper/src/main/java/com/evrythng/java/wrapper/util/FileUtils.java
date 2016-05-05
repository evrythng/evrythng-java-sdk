/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * This class contains utility methods to upload files.
 */
public class FileUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

	public static final String X_AMZ_ACL_HEADER_NAME = "x-amz-acl";
	public static final String X_AMZ_ACL_HEADER_VALUE_PUBLIC_READ = "public-read";
	public static final String X_AMZ_ACL_HEADER_VALUE_PRIVATE = "private";


	/**
	 * Uploads file with public read access.
	 *
	 * @param url         upload url.
	 * @param contentType content type.
	 * @param file        file for upload.
	 */
	public static void uploadPublicFile(final URL url, final String contentType, final File file) throws IOException {

		HttpURLConnection connection = getConnectionForPublicUpload(url, contentType);
		try (OutputStream output = connection.getOutputStream();
		     WritableByteChannel wbc = Channels.newChannel(output);
		     FileInputStream fis = new FileInputStream(file);
		     FileChannel fc = fis.getChannel()) {
			fc.transferTo(0, fc.size(), wbc);
		}
		validateConnectionAfterUpload(connection);
	}

	/**
	 * Uploads file with public read access. Will try to determine content type.
	 *
	 * @param url  upload url.
	 * @param file file for upload.
	 */
	public static void uploadPublicFile(final URL url, final File file) throws IOException {

		String contentType = URLConnection.guessContentTypeFromName(file.getName());
		uploadPublicFile(url, contentType, file);
	}

	/**
	 * Uploads text as a file content with public read access.
	 *
	 * @param url         upload url.
	 * @param contentType content type.
	 * @param content     text content.
	 */
	public static void uploadPublicContent(final URL url, final String contentType, final String content) throws IOException {

		HttpURLConnection connection = getConnectionForPublicUpload(url, contentType);
		try (OutputStream output = connection.getOutputStream();
		     BufferedOutputStream bos = new BufferedOutputStream(output)) {
			bos.write(content.getBytes());
		}
		validateConnectionAfterUpload(connection);
	}

	public static void uploadPrivateContent(final URI uri, final String contentTypeString, final String contentString) throws IOException {

		LOGGER.info("uploadPrivateContent START: uri: [{}]; content type: [{}], content length: [{}]", new Object[] { uri, contentTypeString, contentString.length() });

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

		HttpPut httpPut = new HttpPut(uri);
		httpPut.addHeader(HttpHeaders.CONTENT_TYPE, contentTypeString);

		ContentType contentType = ContentType.create(contentTypeString);
		StringEntity stringEntity = new StringEntity(contentString, contentType);

		httpPut.setEntity(stringEntity);

		CloseableHttpResponse response = closeableHttpClient.execute(httpPut);
		StatusLine statusLine = response.getStatusLine();

		if (!(statusLine.getStatusCode() == HttpStatus.SC_OK)) {
			throw new IOException(String.format("An error occurred while trying to upload private file - %d: %s", statusLine.getStatusCode(), statusLine.getReasonPhrase()));
		}

		LOGGER.info("uploadPrivateContent END: uri: [{}]; content type: [{}], content length: [{}]", new Object[] { uri, contentTypeString, contentString.length() });
	}

	public static void uploadPrivateContent(final URI uri, final String contentTypeString, final File contentFile) throws IOException {

		LOGGER.info("uploadPrivateContent START: uri: [{}]; content type: [{}], content file: [{}]", new Object[] { uri, contentTypeString, contentFile });

		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

		HttpPut httpPut = new HttpPut(uri);
		httpPut.addHeader(HttpHeaders.CONTENT_TYPE, contentTypeString);

		ContentType contentType = ContentType.create(contentTypeString);
		FileEntity fileEntity = new FileEntity(contentFile, contentType);
		httpPut.setEntity(fileEntity);

		CloseableHttpResponse response = closeableHttpClient.execute(httpPut);
		StatusLine statusLine = response.getStatusLine();

		if (!(statusLine.getStatusCode() == HttpStatus.SC_OK)) {
			throw new IOException(String.format("An error occurred while trying to upload private file - %d: %s", statusLine.getStatusCode(), statusLine.getReasonPhrase()));
		}

		LOGGER.info("uploadPrivateContent END: uri: [{}]; content type: [{}], content file: [{}]", new Object[] { uri, contentTypeString, contentFile });
	}

	/**
	 * Reads from {@link InputStream} provided and uploads data to Cloud as a file with public read access.
	 *
	 * @param url         upload url.
	 * @param contentType content type.
	 * @param stream      {@link InputStream} where to read from. Should be closed externally.
	 */
	public static void uploadPublicStream(final URL url, final String contentType, final InputStream stream) throws IOException {

		HttpURLConnection connection = getConnectionForPublicUpload(url, contentType);
		try (OutputStream output = connection.getOutputStream()) {
			IOUtils.copy(stream, output);
		}
		validateConnectionAfterUpload(connection);
	}

	private static HttpURLConnection getConnectionForPublicUpload(final URL url, final String contentType) throws IOException {

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(HttpPut.METHOD_NAME);
		connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, contentType);
		connection.setRequestProperty(X_AMZ_ACL_HEADER_NAME, X_AMZ_ACL_HEADER_VALUE_PUBLIC_READ);
		connection.setDoOutput(true);
		connection.connect();
		return connection;
	}

	private static HttpURLConnection getConnectionForPrivateUpload(final URL url, final String contentType) throws IOException {

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(HttpPut.METHOD_NAME);

		connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, contentType);
		connection.setRequestProperty(X_AMZ_ACL_HEADER_NAME, X_AMZ_ACL_HEADER_VALUE_PRIVATE);
		connection.setDoOutput(true);
		connection.connect();
		return connection;
	}

	private static void validateConnectionAfterUpload(final HttpURLConnection connection) throws IOException {

		int responseCode = connection.getResponseCode();
		if (responseCode == 200) {
			try (final InputStream is = connection.getInputStream()) {
				while (is.read() > 0) {
					// consume
				}
			}
		} else {
			try (final InputStream is = connection.getErrorStream()) {
				final String error = IOUtils.toString(is);
				throw new IOException(
						String.format("Unable to upload file. Got error %d %s: %s", responseCode, connection
								.getResponseMessage(), error));
			}
		}
		connection.disconnect();
	}
}
