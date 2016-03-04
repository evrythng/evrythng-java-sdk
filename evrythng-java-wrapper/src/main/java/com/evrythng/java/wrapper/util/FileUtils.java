/*
 * (c) Copyright 2016 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.util;

import org.apache.commons.io.IOUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * This class contains utility methods to upload files.
 */
public class FileUtils {

	/**
	 * Uploads file with public read access.
	 *
	 * @param url         upload url.
	 * @param contentType content type.
	 * @param file        file for upload.
	 */
	public static void uploadFile(final URL url, final String contentType, final File file) throws IOException {

		HttpURLConnection connection = getConnectionForUpload(url, contentType);
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
	public static void uploadFile(final URL url, final File file) throws IOException {

		String contentType = URLConnection.guessContentTypeFromName(file.getName());
		uploadFile(url, contentType, file);
	}

	/**
	 * Uploads text as a file content with public read access.
	 *
	 * @param url         upload url.
	 * @param contentType content type.
	 * @param content     text content.
	 */
	public static void uploadContent(final URL url, final String contentType, final String content) throws IOException {

		HttpURLConnection connection = getConnectionForUpload(url, contentType);
		try (OutputStream output = connection.getOutputStream();
		     BufferedOutputStream bos = new BufferedOutputStream(output)) {
			bos.write(content.getBytes());
		}
		validateConnectionAfterUpload(connection);
	}

	private static HttpURLConnection getConnectionForUpload(final URL url, final String contentType) throws IOException {

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("PUT");
		connection.setRequestProperty("Content-Type", contentType);
		connection.setRequestProperty("x-amz-acl", "public-read");
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
