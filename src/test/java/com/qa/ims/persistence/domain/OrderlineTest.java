package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderlineTest {

	private Orderline ol;
	private Orderline other;

	@Before
	public void setUp() {
		ol = new Orderline(1L, 2L, 3, 4L);
		other = new Orderline(1L, 2L, 3, 4L);
	}

	@Test
	public void settersTest() {
		assertNotNull(ol.getOrderID());
		assertNotNull(ol.getItemID());
		assertNotNull(ol.getQuantity());
		assertNotNull(ol.getOrderlineID());

		ol.setOrderID(null);
		assertNull(ol.getOrderID());
		ol.setItemID(null);
		assertNull(ol.getItemID());
// NEED TO FIND WAY TO RESOLVE THIS
//		ol.setQuantity(0);
//		assertNull(ol.getQuantity());
		ol.setOrderlineID(null);
		assertNull(ol.getOrderlineID());
	}

	@Test
	public void equalsWithNull() {
		assertFalse(ol.equals(null));
	}

	@Test
	public void createOLWithOLID() {
		assertEquals(1L, ol.getOrderID(), 0);
		assertEquals(2L, ol.getItemID(), 0);
		assertEquals(3, ol.getQuantity(), 0);
		assertEquals(4L, ol.getOrderlineID(), 0);
	}

	@Test
	public void equalsWithDiffObjects() {
		assertFalse(ol.equals(new Object()));
	}

	@Test
	public void checkEquality() {
		assertTrue(ol.equals(ol));
	}

	@Test
	public void checkEqualityBetweenDiffObjs() {
		assertTrue(ol.equals(other));
	}

	@Test
	public void orderIDNullItemIDAndQuantityNotNull() {
		ol.setOrderID(null);
		assertFalse(ol.equals(other));
	}

	@Test
	public void orderIDAndItemIDNullQuantityNotNull() {
		ol.setOrderID(null);
		ol.setItemID(null);
		assertFalse(ol.equals(other));
	}

	@Test
	public void orderIDAndQuantityNullItemIDNotNull() {
		ol.setOrderID(null);
		ol.setQuantity(0);
		assertFalse(ol.equals(other));
	}

	@Test
	public void orderIDsNotEqual() {
		ol.setOrderID(2L);
		assertFalse(ol.equals(other));
	}

	@Test
	public void CheckEqualityBetweenDifferentOrderlinesNullOrderID() {
		ol.setOrderID(null);
		other.setOrderID(null);
		assertTrue(ol.equals(other));
	}

	@Test
	public void nullOrderlineID() {
		ol.setOrderlineID(null);
		assertFalse(ol.equals(other));
	}

	@Test
	public void bothWithNullOrderlineID() {
		ol.setOrderlineID(null);
		other.setOrderlineID(null);
		assertTrue(ol.equals(other));
	}

	@Test
	public void otherOrderlineIDDifferent() {
		other.setOrderID(2L);
		assertFalse(other.equals(ol));
	}

	@Test
	public void nullQuantity() {
		ol.setQuantity(0);
		assertFalse(ol.equals(other));
	}

	@Test
	public void bothWithNullQuantity() {
		ol.setQuantity(0);
		other.setQuantity(0);
		assertTrue(ol.equals(other));
	}

	@Test
	public void otherQuantDifferent() {
		other.setQuantity(1);
		assertFalse(ol.equals(other));
	}

	@Test
	public void nullItemID() {
		ol.setItemID(null);
		assertFalse(ol.equals(other));
	}

	@Test
	public void nullItemIDOnBoth() {
		ol.setItemID(null);
		other.setItemID(null);
		assertTrue(ol.equals(other));
	}

	@Test
	public void otherItemIDDifferent() {
		other.setItemID(3L);
		assertFalse(ol.equals(other));
	}

	@Test
	public void constructorWithoutOrderlineID() {
		Orderline ol = new Orderline(1L, 2L, 3);
		assertNull(ol.getOrderlineID());
		assertNotNull(ol.getOrderID());
		assertNotNull(ol.getItemID());
		assertNotNull(ol.getQuantity());
	}

	@Test
	public void constructorWithoutOrderIDorOrderlineID() {
		Orderline ol = new Orderline(2L, 3);
		assertNull(ol.getOrderlineID());
		assertNull(ol.getOrderID());
		assertNotNull(ol.getItemID());
		assertNotNull(ol.getQuantity());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(ol.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Orderline ol = new Orderline(null, null, 0, null);
		Orderline other = new Orderline(null, null, 0, null);
		assertEquals(ol.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "orderlineID: 4, orderID: 1, itemID: 2, quantity: 3";
		assertEquals(toString, ol.toString());
	}

}
