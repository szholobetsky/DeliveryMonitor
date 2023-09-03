package com.delivery.monitor.schedule;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.delivery.monitor.service.MonitorService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTask {

	@Autowired 
	MonitorService monitorService;
	
	@Scheduled(fixedDelayString = "${period.in.seconds}", timeUnit = TimeUnit.SECONDS)
	public void runTask() {
		monitorService.monitor();
	}
}
