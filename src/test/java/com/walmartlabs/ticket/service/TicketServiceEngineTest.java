package com.walmartlabs.ticket.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.walmartlabs.ticket.data.SeatStatus;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceEngineTest {

	private TicketServiceEngine ticketServiceEngine;

	@Before
	public void setup() {
		ticketServiceEngine = new TicketServiceEngine();
	}

	@Test
	public void testTicketService() {
		int numSeatsAvailable = ticketServiceEngine.numSeatsAvailable();
		assertEquals(297, numSeatsAvailable);

		SeatHold seatHoldTooMany = ticketServiceEngine.findAndHoldSeats(300, "user@test.com");
		assertNull(seatHoldTooMany);

		SeatHold holdTooFewSeats = ticketServiceEngine.findAndHoldSeats(0, "user@test.com");
		assertNull(holdTooFewSeats);

		assertTrue(ticketServiceEngine.isSeatAvailable(0));
		assertFalse(ticketServiceEngine.isSeatReserved(1));
		SeatHold holdSeats = ticketServiceEngine.findAndHoldSeats(2, "user@test.com");
		assertNotNull(holdSeats);
		assertEquals(2, holdSeats.getHoldingSeats().length);

		String result = ticketServiceEngine.reserveSeats(holdSeats.getSeatHoldId(), "user@test.com");
		assertNotNull(result);
		SeatStatus[] reservedStatus = ticketServiceEngine.getReservedSeats();
		assertEquals(2, reservedStatus.length);
		assertFalse(ticketServiceEngine.isSeatAvailable(0));
		assertTrue(ticketServiceEngine.isSeatReserved(1));

		SeatHold seatHoldWait = ticketServiceEngine.findAndHoldSeats(9, "user@test.com");
		assertNotNull(seatHoldWait);
		assertEquals(9, seatHoldWait.getHoldingSeats().length);

		//Pause for 11 seconds
		try {
			Thread.sleep(11000);
		} catch (Exception e) {
		}
		String waitResult = ticketServiceEngine.reserveSeats(seatHoldWait.getSeatHoldId(), "user@test.com");
		assertNull(waitResult);

		SeatHold holdRestSeats = ticketServiceEngine.findAndHoldSeats(295, "user@test.com");
		assertNotNull(holdRestSeats);
		assertEquals(295, holdRestSeats.getHoldingSeats().length);

	}

}
