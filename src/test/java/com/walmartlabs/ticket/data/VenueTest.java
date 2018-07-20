package com.walmartlabs.ticket.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VenueTest {

	@Test
	public void testVenue() {
		Venue venue = Venue.getVenue();
		assertNotNull(venue);

		int[] oldNumOfRowSeats = venue.getNumOfRowSeats();
		int[] newNumOfRowSeats = { 11, 13, 15, 17, 19 };
		venue.setNumOfRowSeats(newNumOfRowSeats);
		assertEquals(75, venue.getNumOfSeats());
		assertEquals(5, venue.getNumOfRows());

		venue.setNumOfRowSeats(oldNumOfRowSeats);
		assertEquals(297, venue.getNumOfSeats());
		assertEquals(9, venue.getNumOfRows());
	}

}
