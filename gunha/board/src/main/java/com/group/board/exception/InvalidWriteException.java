package com.group.board.exception;

public class InvalidWriteException extends RuntimeException {
	public InvalidWriteException() {
		super();
	}

	public InvalidWriteException(String message) {
		super(message);
	}

	public InvalidWriteException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidWriteException(Throwable cause) {
		super(cause);
	}
}
