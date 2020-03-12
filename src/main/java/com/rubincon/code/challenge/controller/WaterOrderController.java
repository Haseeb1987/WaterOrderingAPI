package com.rubincon.code.challenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rubincon.code.challenge.model.WaterOrder;
import com.rubincon.code.challenge.service.IWaterOrder;
import com.rubincon.code.challenge.utility.WaterOrderConstants;

@RestController
@RequestMapping("/order")
public class WaterOrderController {
	
	@Autowired
	private IWaterOrder order;
	
	@GetMapping()
	public List<WaterOrder> findAllWaterOrders(){
		return order.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findWaterOrderById(@PathVariable String id) {
		WaterOrder waterOrder = order.findById(Long.parseLong(id));
		Optional<WaterOrder> waterOrderOptional = Optional.ofNullable(waterOrder);
		if(waterOrderOptional.isPresent()) {
			return new ResponseEntity<Object>(waterOrder, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(WaterOrderConstants.WATER_ORDER_NOT_FOUND, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/order")
	public ResponseEntity<Object> saveWaterOrder(@RequestBody WaterOrder waterOrder){
		Boolean result = order.addWaterOrder(waterOrder);
		if(result) {
			return new ResponseEntity<Object>(WaterOrderConstants.WATER_ORDER_CREATED, HttpStatus.CREATED);
		}
		return new ResponseEntity<Object>(WaterOrderConstants.WATER_ORDER_NOT_CREATED, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateWaterOrderStatus(@PathVariable String id, @RequestParam(name = "status") String status){
		Boolean result = order.updateStatus(Long.parseLong(id), status);
		if(result) {
			return new ResponseEntity<Object>(WaterOrderConstants.WATER_ORDER_CREATED, HttpStatus.CREATED);
		}
		return new ResponseEntity<Object>(WaterOrderConstants.WATER_ORDER_NOT_CREATED, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> cancelWaterOrder(@PathVariable String id){
		Boolean result = order.cancelWaterOrder(Long.parseLong(id));
		if(result) {
			return new ResponseEntity<Object>(WaterOrderConstants.WATER_ORDER_CANCELLED, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(WaterOrderConstants.WATER_ORDER_NOT_FOUND, HttpStatus.NOT_FOUND);
	}
}
