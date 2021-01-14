package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.ItemServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private ItemServices itemServices;

	@Spy
	@InjectMocks
	private ItemController itemController;

	@Test
	public void readAllTest() {
		ItemController itemController = new ItemController(itemServices);
		List<Item> items = new ArrayList<>();
		items.add(new Item("candle", "£5"));
		items.add(new Item("plant", "£12"));
		items.add(new Item("chair", "£30"));
		items.add(new Item("table", "£50"));
		Mockito.when(itemServices.readAll()).thenReturn(items);
		assertEquals(items, itemController.readAll());
	}

	@Test
	public void createTest() {
		String itemName = "candle";
		String itemPrice = "£5";
		Mockito.doReturn(itemName, itemPrice).when(itemController).getInput();
		Item item = new Item(itemName, itemPrice);
		Item savedItem = new Item(1L, "candle", "plant");
		Mockito.when(itemServices.create(item)).thenReturn(savedItem);
		assertEquals(savedItem, itemController.create());
	}

	@Test
	public void updateTest() {
		String itemID = "1";
		String itemName = "candle";
		String itemPrice = "£5";
		Mockito.doReturn(itemID, itemName, itemPrice).when(itemController).getInput();
		Item item = new Item(1L, itemName, itemPrice);
		Mockito.when(itemServices.update(item)).thenReturn(item);
		assertEquals(item, itemController.update());
	}

	@Test
	public void deleteTest() {
		// doesn't return anything - just verify that it calls the delete method
		String itemID = "1";
		Mockito.doReturn(itemID).when(itemController).getInput();
		itemController.delete();
		Mockito.verify(itemServices, Mockito.times(1)).delete(1L);
	}

}
