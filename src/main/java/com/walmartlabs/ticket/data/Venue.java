package com.walmartlabs.ticket.data;

import java.util.Arrays;

/**
* The class keeps seats info.
*
*/
public class Venue {

	private static Venue venueInstance;

	private int[] numOfRowSeats = { 33, 33, 33, 33, 33, 33, 33, 33, 33 };
	private SeatStatus[] seats;

	private Venue() {
	}

	public static Venue getVenue() {
		if (venueInstance == null) {
			venueInstance = new Venue();
		}
		return venueInstance;
	}

	public int getNumOfSeats() {
		return Arrays.stream(numOfRowSeats).sum();
	}

	public int getNumOfRows() {
		return numOfRowSeats.length;
	}

	public void setNumOfRowSeats(int[] numOfRowSeats) {
		this.numOfRowSeats = numOfRowSeats;
	}

	public int[] getNumOfRowSeats() {
		return this.numOfRowSeats;
	}

	public SeatStatus[] getSeats() {
		if (seats == null) {
			seats = new SeatStatus[getNumOfSeats()];
			Arrays.setAll(seats, i -> new SeatStatus());
		}
		return seats;
	}

}
