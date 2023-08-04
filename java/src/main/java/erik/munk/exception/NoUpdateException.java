package erik.munk.exception;

import java.sql.SQLException;

public class NoUpdateException extends SQLException {

	public NoUpdateException(Throwable cause) {
		super(cause);
	}

	public NoUpdateException(Throwable cause, String message) {
		super(message, cause);
	}

	public NoUpdateException(String message) {
		super(message);
	}
}
