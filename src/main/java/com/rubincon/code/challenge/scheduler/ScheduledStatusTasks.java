package com.rubincon.code.challenge.scheduler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rubincon.code.challenge.enums.Status;
import com.rubincon.code.challenge.model.WaterOrder;
import com.rubincon.code.challenge.service.WaterOrderServiceImpl;

@Component
public class ScheduledStatusTasks {
	
	@Autowired
	private WaterOrderServiceImpl waterOrderServiceImpl;

    @Scheduled(fixedRate = 9000)
    public void scheduleTaskWithFixedRate() {
    	
    	List<WaterOrder> waterOrders = new ArrayList<WaterOrder>();
    	waterOrders.addAll(waterOrderServiceImpl.waterOrders);
    	for(WaterOrder order : waterOrders) {
    		if((order.getStatus().equals(Status.REQUESTED.name())) && (order.getStartDateTime().isBefore(LocalDateTime.now()))) {
    			waterOrderServiceImpl.updateStatus(order.getFarmId(), Status.INPROGRESS.name());
    		}
    		if((order.getStatus().equals(Status.INPROGRESS.name())) && (order.getStartDateTime().plusHours(order.getDuration()).isBefore(LocalDateTime.now()))) {
    			waterOrderServiceImpl.updateStatus(order.getFarmId(), Status.DELIVERED.name());
    		}
    	}
    }
}
