package com.strategicgains.cli.exception;

public class UnknownCommandException
extends RuntimeException {

	private static final long serialVersionUID = 4689388887004037469L;

	public UnknownCommandException() {
		super();
	}

	public UnknownCommandException(String message) {
		super(message);
	}

	public UnknownCommandException(Throwable cause) {
		super(cause);
	}

	public UnknownCommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
