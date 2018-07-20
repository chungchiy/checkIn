package com.walmartlabs.ticket.service;

import com.walmartlabs.ticket.data.SeatStatus;

/**
* The class object will be returned by findAndHoldSeats() method.
*
*/
public class SeatHold {

	private int seatHoldId;
	private boolean holdStatus;
	private SeatStatus[] holdingSeats;
	private String customerEmail;

	public void setSeatHoldId(int seatHoldId) {
		this.seatHoldId = seatHoldId;
	}

	public int getSeatHoldId() {
		return this.seatHoldId;
	}

	public void setHoldStatus(boolean holdStatus) {
		this.holdStatus = holdStatus;
	}

	public boolean getHoldStatus() {
		return this.holdStatus;
	}

	public void setHoldingSeats(SeatStatus[] holdingSeats) {
		this.holdingSeats = holdingSeats;
	}

	public SeatStatus[] getHoldingSeats() {
		return this.holdingSeats;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerEmail() {
		return this.customerEmail;
	}

}
