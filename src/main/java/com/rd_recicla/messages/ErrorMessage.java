package com.rd_recicla.messages;

public class ErrorMessage extends Message implements IMessage{

	
	public ErrorMessage() {
		
	}
	
	public ErrorMessage(String message) {
		super(message);
	}
	
	
	@Override
	public String message() {
		return getMessage();
	}

}
