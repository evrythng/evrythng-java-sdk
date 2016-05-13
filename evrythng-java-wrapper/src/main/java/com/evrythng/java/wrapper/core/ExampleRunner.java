/*
 * (c) Copyright Reserved EVRYTHNG Limited 2016. All rights reserved.
 * Use of this material is subject to license.
 * Copying and unauthorised use of this material strictly prohibited.
 */
package com.evrythng.java.wrapper.core;

import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.commons.config.ApiConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Base definition for implementing examples on top of the EVRYTHNG API wrapper.
 */
public abstract class ExampleRunner {

	private static final Logger logger = LoggerFactory.getLogger(ExampleRunner.class);

	private final ApiConfiguration config;

	/**
	 * Creates a new instance of {@link ExampleRunner} using the provided {@link ApiConfiguration}.
	 *
	 * @param config {@link ApiConfiguration} instance
	 */
	protected ExampleRunner(final ApiConfiguration config) {
		this.config = config;
	}

	/**
	 * Runs the current example.
	 *
	 * @throws EvrythngException
	 */
	public void run() throws EvrythngException {
		try {
			// Delegate:
			doRun();
			echo("Done! Checkout others examples for more usages of the EVRYTHNG API wrapper.");
		} catch (EvrythngException e) {
			echo("[EvrythngException] ", e.getMessage());
			throw e;
		}
	}

	/**
	 * Concrete implementation.
	 *
	 * @throws EvrythngException
	 */
	protected abstract void doRun() throws EvrythngException;

	/**
	 * Extracts required parameters from program {@code args}.
	 *
	 * @param args    program arguments
	 * @return {@link ApiConfiguration} instance
	 */
	public static ApiConfiguration extractConfig(final String[] args) {
		ApiConfiguration config = new ApiConfiguration();
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--key") && i + 1 < args.length) {
				config.setKey(args[i + 1]);
			} else if (args[i].equals("--url") && i + 1 < args.length) {
				config.setUrl(args[i + 1]);
			}
		}
		return config;
	}

	/**
	 * Displays the usage information.
	 */
	public static void usage() {
		echo(">> Usage:");
		echo("   --key: The EVRYTHNG API key for authentication.\n");
		echo("   --url: [Optional] The EVRYTHNG API base URL.\n");
	}

	/**
	 * Logs the provided message.
	 *
	 * @param message message
	 */
	public static void echo(final String message) {
		logger.info("[ECHO] {}", message);
	}

	/**
	 * Logs an INFO message according to the specified format and arguments. Arguments will be jsonified before string
	 * replacement.
	 *
	 * @param format format
	 * @param args arguments
	 */
	public static void echo(final String format, final Object... args) {
		// Jsonify data objects:
		List<Object> argsAsJson = new ArrayList<>();
		for (Object arg : args) {
			argsAsJson.add(JSONUtils.write(arg));
		}
		logger.info("[ECHO] " + format, argsAsJson.toArray());
	}

	public ApiConfiguration getConfig() {
		return config;
	}
}
