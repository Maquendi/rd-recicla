package com.rd_recicla.messages;

public class SuccessMessage extends Message implements IMessage{

	public SuccessMessage(String message) {
		super(message);
	}
	
	public SuccessMessage() {
		
	}
	
	@Override
	public String message() {
		return getMessage();
	}

}
