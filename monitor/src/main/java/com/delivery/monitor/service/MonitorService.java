package com.delivery.monitor.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.net.http.HttpClient;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class MonitorService {

	private static final Logger log = LoggerFactory.getLogger(MonitorService.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	public void monitor() {
		
		// TODO get min and max ID of active deliveries
		
		// TODO if min and max == 0 then request all delivery that is not delivered yet
		// TODO if min and max is not null then select all delivery with deliveryID > minId
		
		// TODO for deliveries with ID < max send POST request and update delivery in h2. 
		// TODO for id> max or if min and max == 0 then send PUT request and create new delivery in h2 
		log.info("The time is now {}", dateFormat.format(new Date()));
	}
}
