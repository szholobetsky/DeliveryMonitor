package com.delivery.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.monitor.connector.EtcdConnector;
import com.delivery.monitor.connector.TrackerConnector;
import com.delivery.monitor.connector.ZooKeeperConnector;
import com.delivery.monitor.model.ActiveDeliveryRange;
import com.delivery.monitor.repository.DeliveryRepository;
import com.delivery.monitor.model.Delivery;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.io.IOException;
import java.net.http.HttpClient;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class MonitorService {

	@Autowired
	TrackerConnector trackerConnector;
	
	@Autowired
	DeliveryRepository deliveryRepository;
	
	// TODO delete when finish ZooKeper integration
	// @Autowired
	// EtcdConnector etcdConnector;
	
	@Autowired
	ZooKeeperConnector zooKeeperConnector;
	
	private static final Logger log = LoggerFactory.getLogger(MonitorService.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	private static final String STATUS_DELIVERED = "Order Delivered";

	public void monitor() {
		try {
			// get trackers instances 
			// TODO delete when finish ZooKeper integration
			// List<String> trackers = etcdConnector.getTrackers();
			List<String> trackers = zooKeeperConnector.getTrackers();
			if (trackers.size() == 0) {
				log.error("No trackers registered yet");
				return;
			}
			trackers.sort(Comparator.naturalOrder()); //tracker with smallest IP is main
			// get min and max ID of active deliveries
			ActiveDeliveryRange range = trackerConnector.getDeliveriesRange(trackers.get(0));
		
			List<Delivery> deliveries;
			
			// if min and max == 0 then request all delivery that is not delivered yet
			// if min and max is not null then select all delivery with deliveryID > minId
			if (range.getMinId() ==0 && range.getMaxId() ==0) {
				deliveries = deliveryRepository.findByDeliveryStatusIsNot(STATUS_DELIVERED);
			} 
			else {
				deliveries = deliveryRepository.findByDeliveryIdGreaterThan(range.getMinId());
			}
			
			// send all selected deliveries to tracker for update local track db
			for(String tracker:trackers) { 
				trackerConnector.sendDeliveriesToTracker(tracker,deliveries);
			}
			log.info("The time is now {}", dateFormat.format(new Date()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		} 
		// TODO delete when finish ZooKeper integration
		/*
		 * catch (ExecutionException e) { // TODO Auto-generated catch block
		 * log.error(e.getMessage()); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block log.error(e.getMessage()); }
		 */
	}
}
