package com.walmartlabs.ticket.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SeatStatusTest {

	@Test
	public void testReservedStatus() {
		SeatStatus seatStatus = new SeatStatus();
		assertFalse(seatStatus.isReserved());
		seatStatus.setReserved(true);
		assertTrue(seatStatus.isReserved());
	}

	@Test
	public void testHeldStatus() {
		SeatStatus seatStatus = new SeatStatus();
		assertNull(seatStatus.getHeldTimestamp());
		seatStatus.setHeldTimestamp(new Timestamp(System.currentTimeMillis()));
		assertNotNull(seatStatus.getHeldTimestamp());
	}

	@Test
	public void testAvailableStatus() {
		SeatStatus seatStatus = new SeatStatus();
		assertTrue(seatStatus.isAvailable());
		seatStatus.setHeldTimestamp(new Timestamp(System.currentTimeMillis()));
		assertFalse(seatStatus.isAvailable());
		seatStatus.setHeldTimestamp(null);
		seatStatus.setReserved(true);
		assertFalse(seatStatus.isAvailable());
	}

}
