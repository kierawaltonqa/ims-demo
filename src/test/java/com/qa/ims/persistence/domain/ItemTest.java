package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	private Item item;
	private Item other;

	@Before
	public void setUp() {
		item = new Item(1L, "candle", 5.0);
		other = new Item(1L, "candle", 5.0);
	}

	@Test
	public void settersTest() {
		assertNotNull(item.getItemID());
		assertNotNull(item.getItemName());
		assertNotNull(item.getItemPrice());

		item.setItemID(null);
		assertNull(item.getItemID());
		item.setItemName(null);
		assertNull(item.getItemName());
		item.setItemPrice(null);
		assertNull(item.getItemPrice());
	}

	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}

	@Test
	public void createItemWithItemID() {
		assertEquals(1L, item.getItemID(), 0);
		assertEquals("candle", item.getItemName());
		assertEquals(5.0, item.getItemPrice(), 0);
	}

	@Test
	public void equalsWithDifferentObjects() {
		assertFalse(item.equals(new Object()));
	}

	@Test
	public void checkEquality() {
		assertTrue(item.equals(item));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(item.equals(other));
	}

	@Test
	public void itemNameNullButItemPriceNotNull() {
		item.setItemName(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void itemNamesNotEqual() {
		other.setItemName("plant");
		assertFalse(item.equals(other));
	}

	@Test
	public void CheckEqualityBetweenDifferentObjectsNullItemName() {
		item.setItemName(null);
		other.setItemName(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void nullID() {
		other.setItemID(null);
		assertFalse(other.equals(item));
	}

	@Test
	public void bothWithNullID() {
		other.setItemID(null);
		item.setItemID(null);
		assertTrue(other.equals(item));
	}

	@Test
	public void otherIDDifferent() {
		other.setItemID(2L);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullPrice() {
		other.setItemPrice(null);
		assertFalse(other.equals(item));
	}

	@Test
	public void nullPriceOnBoth() {
		other.setItemPrice(null);
		item.setItemPrice(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void otherPriceIsDifferent() {
		other.setItemPrice(10.0);
		assertFalse(other.equals(item));
	}

	@Test
	public void constructorWithoutID() {
		Item item = new Item("candle", 5.0);
		assertNull(item.getItemID());
		assertNotNull(item.getItemName());
		assertNotNull(item.getItemPrice());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(item.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Item item = new Item(null, null);
		Item other = new Item(null, null);
		assertEquals(item.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "itemID: 1, itemName: candle, itemPrice: 5";
		assertEquals(toString, item.toString());

	}

}
