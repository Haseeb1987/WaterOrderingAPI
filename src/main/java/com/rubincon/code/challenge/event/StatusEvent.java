package com.rubincon.code.challenge.event;

import org.springframework.context.ApplicationEvent;

public class StatusEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public StatusEvent(Object source, String message) {
		super(source);
		this.message = message;
	}

	@Override
	public String toString() {
		return message.toString();
	}

}
