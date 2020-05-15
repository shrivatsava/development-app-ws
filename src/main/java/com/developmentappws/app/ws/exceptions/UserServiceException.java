package com.developmentappws.app.ws.exceptions;

public class UserServiceException extends RuntimeException{


	private static final long serialVersionUID = 308011936883867078L;

	public UserServiceException(String message)
	{
		super(message);
	}
}
