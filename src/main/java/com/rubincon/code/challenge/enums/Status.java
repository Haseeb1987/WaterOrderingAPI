package com.rubincon.code.challenge.enums;

public enum Status {
	REQUESTED ("Order has been placed but not yet delivered."),
	INPROGRESS ("Order is being delivered right now."),
	DELIVERED ("Order has been delivered."),
	CANCELLED ("Order was cancelled before delivery.");
	
	private String action;
	
	private Status(String action) {
		this.action = action;
	}
	
	 @Override
    public String toString() {
        return action;
    }
	
}
