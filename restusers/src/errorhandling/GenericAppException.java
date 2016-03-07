package errorhandling;

public class GenericAppException extends Exception {

	public GenericAppException() {
	}

	public GenericAppException(String message) {
		super(message);
	}
	
	public GenericAppException(Throwable cause) {
		super(cause);
	}

	public GenericAppException(String message, Throwable cause) {
		super(message, cause);
	}


}
