package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	private Order order;
	private Order other;

	@Before
	public void setUp() {
		order = new Order(1L, 2L, 10.0);
		other = new Order(1L, 2L, 10.0);
	}

	@Test
	public void settersTest() {
		assertNotNull(order.getOrderID());
		assertNotNull(order.getCustomerID());
		assertNotNull(order.getTotalPrice());

		order.setOrderID(null);
		assertNull(order.getOrderID());
		order.setCustomerID(null);
		assertNull(order.getCustomerID());
		order.setTotalPrice(null);
		assertNull(order.getTotalPrice());
	}

	@Test
	public void equalsWithNull() {
		assertFalse(order.equals(null));
	}

	@Test
	public void createOrderWithOrderID() {
		assertEquals(1L, order.getOrderID(), 0);
		assertEquals(2L, order.getCustomerID(), 0);
		assertEquals(10.0, order.getTotalPrice(), 0);
	}

	@Test
	public void equalsWithDifferentOrders() {
		assertFalse(order.equals(new Object()));
	}

	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}

	@Test
	public void equalityBetweenDifferentObjects() {
		assertTrue(order.equals(other));
	}

	@Test
	public void customerIDNullTotalPriceNotNull() {
		order.setCustomerID(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void customerIDNotEqual() {
		order.setCustomerID(3L);
		assertFalse(order.equals(other));
	}

	@Test
	public void checkEqualityBetweenDiffObjectsNullCustomerID() {
		order.setCustomerID(null);
		other.setCustomerID(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void nullOrderID() {
		order.setOrderID(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void bothNullOrderID() {
		order.setOrderID(null);
		other.setOrderID(null);
		assertTrue(other.equals(order));
	}

	@Test
	public void otherOrderIDDifferent() {
		other.setOrderID(2L);
		assertFalse(other.equals(order));
	}

	@Test
	public void nullTotalPrice() {
		order.setTotalPrice(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void bothWithNullTotalPrice() {
		order.setTotalPrice(null);
		other.setTotalPrice(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherTotalPriceDifferent() {
		other.setTotalPrice(100.0);
		assertFalse(other.equals(order));
	}

	@Test
	public void constructorWithoutOrderID() {
		Order order = new Order(2L, 10.0);
		assertNull(order.getOrderID());
		assertNotNull(order.getCustomerID());
		assertNotNull(order.getTotalPrice());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(order.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Order order = new Order(null, null);
		Order other = new Order(null, null);
		assertEquals(order.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "orderID=1, customerID=2, totalPrice=10.0";
		assertEquals(toString, order.toString());
	}

}
