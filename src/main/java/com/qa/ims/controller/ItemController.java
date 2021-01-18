package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in item details for CRUD functionality
 */

public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = Logger.getLogger(ItemController.class);

	private CrudServices<Item> itemService;

	public ItemController(CrudServices<Item> itemService) {
		this.itemService = itemService;
	}

	String getInput() {
		return Utils.getInput();
	}

	/*
	 * this readAll method will read all orders to the logger
	 */

	@Override
	public List<Item> readAll() {
		List<Item> items = itemService.readAll();
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	/*
	 * this create method will create an item by taking a user input
	 */

	@Override
	public Item create() {
		LOGGER.info("Enter the name of the item you wish to create");
		String itemName = getInput();
		LOGGER.info("Now enter the price of this item");
		Double itemPrice = Double.valueOf(getInput());
		Item item = itemService.create(new Item(itemName, itemPrice));
		LOGGER.info("The item has been created");
		return item;
	}
	/*
	 * this update method allows the user to update items in the item table in the
	 * database
	 */

	@Override
	public Item update() {
		LOGGER.info("Please enter the ID of the item you would like to update");
		Long itemID = Long.valueOf(getInput()); // need to use casting here
		LOGGER.info("You need to enter the updated details of this item");
		LOGGER.info("Enter the name of the item");
		String itemName = getInput();
		LOGGER.info("enter the price of the item");
		Double itemPrice = Double.valueOf(getInput());
		Item item = itemService.update(new Item(itemID, itemName, itemPrice));
		LOGGER.info("Item updated");
		return item;

	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the ID of the item you would like to delete");
		Long itemID = Long.valueOf(getInput());
		itemService.delete(itemID);
		LOGGER.info("Item deleted");

	}

}
