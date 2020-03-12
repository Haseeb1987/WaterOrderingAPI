package com.rubincon.code.challenge.validator;

import org.springframework.stereotype.Component;

import com.rubincon.code.challenge.enums.Status;
import com.rubincon.code.challenge.exception.WaterOrderValidationException;
import com.rubincon.code.challenge.model.WaterOrder;
import com.rubincon.code.challenge.utility.WaterOrderConstants;

@Component
public class WaterOrderValidator {

	public WaterOrder validateInDTO(WaterOrder waterOrder) {
		if(waterOrder.getFarmName() == null) {
			throw new WaterOrderValidationException(WaterOrderConstants.WATER_ORDER_FARM_NAME_NOT_EMPTY);
		}
		if(waterOrder.getStatus() == null || waterOrder.getStatus().length() == 0) {
			waterOrder.setStatus(Status.REQUESTED.name());
		}
		if(waterOrder.getDuration() <= 0){
			throw new WaterOrderValidationException(WaterOrderConstants.WATER_ORDER_DURATION_GREATER_THAN_ZERO);
		}
		if(waterOrder.getStartDateTime() == null) {
			throw new WaterOrderValidationException(WaterOrderConstants.WATER_ORDER_START_DATE_NOT_EMPTY);
		}
		return waterOrder;
	}
}
