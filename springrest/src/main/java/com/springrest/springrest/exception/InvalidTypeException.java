package com.springrest.springrest.exception;


public class InvalidTypeException extends RuntimeException
{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidTypeException(String message)
	{
		super(message);
	}
}
