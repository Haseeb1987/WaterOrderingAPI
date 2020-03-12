package com.rubincon.code.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.rubincon.code.challenge.enums.Status;
import com.rubincon.code.challenge.event.StatusEvent;
import com.rubincon.code.challenge.exception.WaterOrderValidationException;
import com.rubincon.code.challenge.model.WaterOrder;
import com.rubincon.code.challenge.utility.Utils;
import com.rubincon.code.challenge.utility.WaterOrderConstants;
import com.rubincon.code.challenge.validator.WaterOrderValidator;

@Service
public class WaterOrderServiceImpl implements IWaterOrder, ApplicationEventPublisherAware{
	
	@Autowired
	private WaterOrderValidator validator;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	@Autowired
    private MessageSource messageSource;
	
	public List<WaterOrder> waterOrders = new ArrayList<WaterOrder>();
	public static long id = 1;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public WaterOrder findById(long id) {
		for(WaterOrder order : waterOrders) {
			if(order.getFarmId() == id) {
				return order;
			}
		}
		return null;
	}
	
	@Override
	public List<WaterOrder> findByName(String name) {
		List<WaterOrder> orders = new ArrayList<WaterOrder>();
		for(WaterOrder order : waterOrders) {
			if(order.getFarmName().equalsIgnoreCase(name)) {
				orders.add(order);
			}
		}
		return orders;
	}

	@Override
	public List<WaterOrder> findAll() {
		List<WaterOrder> orders = new ArrayList<WaterOrder>(waterOrders.size());
		for(WaterOrder order : waterOrders) {
			orders.add(new WaterOrder(order));
		}
		for(WaterOrder order : orders) {
			order.setStatus(Status.valueOf(order.getStatus()).toString());
		}
		return orders;
	}

	@Override
	public Boolean addWaterOrder(WaterOrder order) {
		order = validator.validateInDTO(order);
		if(order.getFarmId() == null) {
			order.setFarmId(id++);
		}
		List<WaterOrder> waterOrders = findByName(order.getFarmName());
		Optional<List<WaterOrder>> waterOrderOptional = Optional.ofNullable(waterOrders);
		if(waterOrderOptional.isPresent()) {
			for(WaterOrder waterOrder : waterOrders) {
				if(!Utils.validateOverlap(waterOrder.getStartDateTime(), waterOrder.getDuration(), order.getStartDateTime(), order.getDuration())) {
					throw new WaterOrderValidationException(WaterOrderConstants.WATER_ORDER_ALREADY_EXIST + waterOrder.getFarmName());
				}
			}
		}
		publishStatusEvent(order);
		return this.waterOrders.add(order);
	}
	
	@Override
	public Boolean updateStatus(long id, String status) {
		WaterOrder waterOrder = findById(id);
		waterOrder.setStatus(status);
		publishStatusEvent(waterOrder);
		return true;
	}

	@Override
	public Boolean cancelWaterOrder(long id) {
		WaterOrder waterOrder = findById(id);
		if(waterOrder != null) {
			if(!waterOrder.getStatus().equals(Status.DELIVERED.name())) {
				waterOrder.setStatus(Status.CANCELLED.name());
				publishStatusEvent(waterOrder);
				return waterOrders.remove(waterOrder);
			}
			throw new WaterOrderValidationException(WaterOrderConstants.WATER_ORDER_ALREADY_DELIVERED);
		}
		return false;
	}
	
	public void publishStatusEvent(WaterOrder waterOrder) {
		this.applicationEventPublisher.publishEvent(new StatusEvent(this, Utils.createStatusMessage(waterOrder, messageSource)));
	}
}
