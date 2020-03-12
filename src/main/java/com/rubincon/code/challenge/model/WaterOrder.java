package com.rubincon.code.challenge.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WaterOrder{
	private Long farmId;
	private String farmName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDateTime;
	private Long duration;
	private String status;
	
	public WaterOrder() {}
	
	public WaterOrder(long farmId, String farmName, LocalDateTime startDateTime, long duration, String status){
		super();
		this.farmId = farmId;
		this.farmName = farmName;
		this.startDateTime = startDateTime;
		this.duration = duration;
		this.status = status;
	}
	
	public WaterOrder(WaterOrder order) {
		super();
		this.farmId = order.getFarmId();
		this.farmName = order.getFarmName();
		this.startDateTime = order.getStartDateTime();
		this.duration = order.getDuration();
		this.status = order.getStatus();
	}

	public Long getFarmId() {
		return farmId;
	}
	
	public void setFarmId(Long farmId) {
		this.farmId = farmId;
	}
	
	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}
	
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	
	public Long getDuration() {
		return duration;
	}
	
	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
