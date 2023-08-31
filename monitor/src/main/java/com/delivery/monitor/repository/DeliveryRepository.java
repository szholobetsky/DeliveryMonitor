package com.delivery.monitor.repository;

import com.delivery.monitor.model.Delivery;
import java.util.List;

public interface DeliveryRepository extends ReadOnlyRepository<Delivery, Integer> {

	List<Delivery> findByDeliveryStatusIsNot(String deliveryStatus);
	List<Delivery> findByDeliveryIdGreaterThan(Integer deliveryId);
	
}
