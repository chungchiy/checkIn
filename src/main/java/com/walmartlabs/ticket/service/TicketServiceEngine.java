package com.walmartlabs.ticket.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import com.walmartlabs.ticket.data.SeatStatus;
import com.walmartlabs.ticket.data.Venue;

/**
* Implementation class for TicketService interface.
*
*/
public class TicketServiceEngine implements TicketService {

	private int seatHoldId = 1;
	private SeatStatus[] seatsData;
	private HashMap<Integer, SeatHold> seatHoldMap = new HashMap<Integer, SeatHold>();

	public TicketServiceEngine() {
		seatsData = Venue.getVenue().getSeats();
	}

	@Override
	public int numSeatsAvailable() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Arrays.stream(seatsData).forEach(s -> s.verifyHold(now));
		long count = Arrays.stream(seatsData).filter(s -> s.isAvailable()).count();
		return Math.toIntExact(count);
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Arrays.stream(seatsData).forEach(s -> s.verifyHold(now));
		SeatStatus[] holdingSeats = Arrays.stream(seatsData).filter(s -> s.isAvailable()).limit(numSeats).toArray(SeatStatus[]::new);
		if (holdingSeats.length> 0 && holdingSeats.length == numSeats) {
			Arrays.stream(holdingSeats).forEach(s -> s.setHeldTimestamp(now));
			SeatHold seatHold = new SeatHold();
			seatHold.setSeatHoldId(seatHoldId);
			seatHold.setHoldStatus(true);
			seatHold.setHoldingSeats((SeatStatus[])holdingSeats);
			seatHold.setCustomerEmail(customerEmail);
			seatHoldMap.put(seatHoldId++, seatHold);
			return seatHold;
		}
		return null;
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) {
		SeatHold seatHold = seatHoldMap.get(seatHoldId);
		if (seatHold != null) {
			SeatStatus[] holdingSeats = seatHold.getHoldingSeats();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			Arrays.stream(holdingSeats).forEach(s -> s.verifyHold(now));

			long expiredSeatsCount = Arrays.stream(holdingSeats).filter(s -> s.isAvailable()).count();
			if (expiredSeatsCount > 0) {
				return null;
			}

			Arrays.stream(holdingSeats).forEach(s -> s.setReserved(true));
			seatHoldMap.remove(seatHoldId);
			UUID uuid = UUID.randomUUID();
			return uuid.toString();
		}
		return null;
	}

	public SeatStatus[] getReservedSeats() {
		SeatStatus[] reservedSeats = Arrays.stream(seatsData).filter(s -> s.isReserved()).toArray(SeatStatus[]::new);
		return reservedSeats;
	}

	public boolean isSeatAvailable(int seatNumber) {
		if (0 <= seatNumber && seatNumber < seatsData.length) {
			return seatsData[seatNumber].isAvailable();
		}
		return false;
	}

	public boolean isSeatReserved(int seatNumber) {
		if (0 <= seatNumber && seatNumber < seatsData.length) {
			return seatsData[seatNumber].isReserved();
		}
		return false;
	}

}
