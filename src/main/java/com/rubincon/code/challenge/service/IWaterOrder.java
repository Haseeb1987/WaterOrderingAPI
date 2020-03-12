package com.rubincon.code.challenge.service;

import java.util.List;

import com.rubincon.code.challenge.model.WaterOrder;

public interface IWaterOrder {
	public WaterOrder findById(long id);
	public List<WaterOrder> findByName(String name);
	public List<WaterOrder> findAll();
	public Boolean addWaterOrder(WaterOrder order);
	public Boolean cancelWaterOrder(long id);
	public Boolean updateStatus(long id, String status);
}
