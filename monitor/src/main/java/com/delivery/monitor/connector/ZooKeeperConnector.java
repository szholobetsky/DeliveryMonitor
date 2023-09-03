package com.delivery.monitor.connector;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class ZooKeeperConnector {	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public List<String> getTrackers() {
	    List<ServiceInstance> list = discoveryClient.getInstances("tracker");
	    List<String> urlList = new ArrayList<String>();
	    if (list != null && list.size() > 0 ) {
	        for (ServiceInstance service : list) {
	        	urlList.add(service.getUri().toString());
	        }
	    	return urlList;
	    }
	    return null;
	}
}
