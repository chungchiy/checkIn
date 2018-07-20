package com.walmartlabs.ticket.data;

import java.sql.Timestamp;

import com.walmartlabs.ticket.config.AppConfig;

/**
* The class object is for keeping seat status.
*
*/
public class SeatStatus {

	private boolean reserved = false;
	private Timestamp heldTimestamp;

	public boolean isReserved() {
		return this.reserved;
	}

	public void setReserved(boolean reserved) {
		if (getHeldTimestamp() != null) {
			setHeldTimestamp(null);
		}
		this.reserved = reserved;
	}

	public void setHeldTimestamp(Timestamp heldTimestamp) {
		this.heldTimestamp = heldTimestamp;
	}

	public Timestamp getHeldTimestamp() {
		return this.heldTimestamp;
	}

	public boolean isAvailable() {
		return !this.reserved && this.heldTimestamp == null;
	}

	public void verifyHold(Timestamp when) {
		if (heldTimestamp != null && (when.getTime() - heldTimestamp.getTime()) > AppConfig.holdTimeout) {
			setHeldTimestamp(null);
		}
	}
	
}
