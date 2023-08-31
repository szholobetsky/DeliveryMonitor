package com.delivery.monitor.model;

import lombok.Getter;
import lombok.Setter;

public class ActiveDeliveryRange {

		private Integer minId = 0; 
		private Integer maxId = 0;
		
		public Integer getMinId() {
			return minId;
		}
		public void setMinId(Integer minId) {
			this.minId = minId;
		}
		public Integer getMaxId() {
			return maxId;
		}
		public void setMaxId(Integer maxId) {
			this.maxId = maxId;
		}
		
}
