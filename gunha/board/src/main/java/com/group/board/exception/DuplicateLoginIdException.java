package com.group.board.exception;

public class DuplicateLoginIdException extends RuntimeException {
	public DuplicateLoginIdException() {
		super();
	}

	public DuplicateLoginIdException(String message) {
		super(message);
	}

	public DuplicateLoginIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateLoginIdException(Throwable cause) {
		super(cause);
	}
}
