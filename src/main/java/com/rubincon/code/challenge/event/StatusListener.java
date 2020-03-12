package com.rubincon.code.challenge.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StatusListener implements ApplicationListener<StatusEvent>{
	
	public StatusListener() {}

	@Override
	public void onApplicationEvent(StatusEvent event) {
		System.out.println("********************************** " + event.toString() + " **********************************");
	}
}
