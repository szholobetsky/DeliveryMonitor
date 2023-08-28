package com.delivery.monitor.scheduled;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.delivery.monitor.service.MonitorService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class scheduledTask {

	@Autowired 
	MonitorService monitorService;
	
	@Scheduled(fixedDelay = 300, timeUnit = TimeUnit.SECONDS)
	public void runTask() {
		monitorService.monitor();
	}
}
