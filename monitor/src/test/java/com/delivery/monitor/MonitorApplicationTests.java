package com.delivery.monitor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.delivery.monitor.service.MonitorService;

@SpringBootTest
class MonitorApplicationTests {

	@Autowired
	MonitorService monitorService;
	
	@Test
	void contextLoads() {
		assertThat(monitorService).isNotNull();
	}

}
