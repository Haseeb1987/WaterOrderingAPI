package com.rubincon.code.challenge.utility;

import java.time.LocalDateTime;

import org.springframework.context.MessageSource;

import com.rubincon.code.challenge.enums.Status;
import com.rubincon.code.challenge.model.WaterOrder;

public class Utils {
	
	public static Boolean validateOverlap(LocalDateTime prevDateTime, long prevDuration, LocalDateTime newDateTime, long newDuration) {
		return (newDateTime.isAfter(prevDateTime.plusHours(prevDuration)) || newDateTime.plusHours(newDuration).isBefore(prevDateTime)); 
	}
	
	public static String createStatusMessage(WaterOrder waterOrder, MessageSource messageSource) {
		if(waterOrder.getStatus().equals(Status.REQUESTED.name())) {
			return messageSource.getMessage("requested", new Object [] {waterOrder.getFarmName()}, "Farm", null);
		}
		else if(waterOrder.getStatus().equals(Status.INPROGRESS.name())) {
			return messageSource.getMessage("inprogress", new Object [] {waterOrder.getFarmName()}, "Farm", null);
		}
		else if(waterOrder.getStatus().equals(Status.DELIVERED.name())) {
			return messageSource.getMessage("delivered", new Object [] {waterOrder.getFarmName()}, "Farm", null);
		}
		else if(waterOrder.getStatus().equals(Status.CANCELLED.name())) {
			return messageSource.getMessage("cancelled", new Object [] {waterOrder.getFarmName()}, "Farm", null);
		}
		return "";
	}
}
