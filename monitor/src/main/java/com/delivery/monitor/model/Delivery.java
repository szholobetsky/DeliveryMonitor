package com.delivery.monitor.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Delivery {
	
	@Id
	@Column(name="DELIVERY_ID")
	private Integer deliveryId;
	
	@Column(name="CUSTOMER_TYPE")
	private String customerType;
	 
	@Column(name="DELIVERY_STATUS")
	private String deliveryStatus;
	
	@Column(name="DELIVERY_EXPECTED_TIME")
	private Timestamp deliveryExpecteTime;
	
	@Column(name="TIME_TO_REACH_DEST")
	private Timestamp timeToReachDest;
	
	@Column(name="CREATED_TIME")
	private Timestamp createdTime;
	
	@Column(name="CURRENT_DISTANCE_TO_DEST")
	private Integer currentDistanceToDest;
	
	@Column(name="TOTAL_DISTANCE_TO_DEST")
	private Integer totalDistanceToDest;
	
	@Column(name="RESTAURANT_AVG_TIME")
	private Integer restaurantAvgTime;
	
	@Column(name="RIDER_RATING")
	private String riderRating;
	
}
