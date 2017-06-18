package ru.javabegin.training.springwebflow.exceptions;

public class UserExistException extends Exception{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2491478887730446910L;

	public UserExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UserExistException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
