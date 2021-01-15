package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	private CrudServices<Order> orderService;
	private CrudServices<Item> itemService;

	public OrderController(CrudServices<Order> orderService, CrudServices<Item> itemService) {
		super();
		this.orderService = orderService;
		this.itemService = itemService;
	}

	String getInput() {
		return Utils.getInput();
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	@Override
	public Order create() {
		List<Item> items = itemService.readAll();
		LOGGER.info("enter the customerID of the order you wish to create");
		Long customerID = Long.valueOf(getInput());
		LOGGER.info("enter the ID of the item you want to add");
		Long itemID = Long.valueOf(getInput());
		boolean bool = items.contains(itemService.create(new Item(itemID)));
		while (bool == true) {
			LOGGER.info("enter the quantity of this item");
			int quantity = Integer.parseInt(getInput());
			LOGGER.info("enter the total price of this order");
			int totalPrice = Integer.parseInt(getInput());
			Order order = orderService.create(new Order(customerID, totalPrice, itemID, quantity));
			return order;
		}
		return null;

//		while(getInput() != "quit") {
//			Long itemID = Long.valueOf(getInput());
//			boolean bool = items.contains(itemService.create(new Item(itemID)));
//			if(bool == true) {
//				LOGGER.info("enter the quantity of this item");
//				int quantity = Integer.parseInt(getInput());
//				Order order = orderService.create(new Order(customerID, itemID, quantity));
//				return order;
//			}
//		}LOGGER.info(");
	}

//		LOGGER.info("enter the the ID of an item you wish to add to this order");
//		Long itemID = Long.valueOf(getInput());
//		LOGGER.info("enter the quantity of this item you would like to add");
//		int quantity = Integer.parseInt(getInput());
//		LOGGER.info("enter the total price of this order");
//		String totalPrice = getInput();
//		Order order = orderService.create(new Order(customerID, totalPrice, itemID, quantity));
//		return order;
//	}

	@Override
	public Order update() {
		LOGGER.info("enter the ID of the order you wish to update");
		Long orderID = Long.valueOf(getInput());
		LOGGER.info("enter the ID of the customer corresponding to this order");
		Long customerID = Long.valueOf(getInput());
		LOGGER.info("enter the ID of an item you wish to add to the order");
		Long itemID = Long.valueOf(getInput());
		LOGGER.info("enter the quantity of this item you wish to add");
		int quantity = Integer.parseInt(getInput());
		LOGGER.info("enter the total price of this order");
		int totalPrice = Integer.parseInt(getInput());

		Order order = orderService.create(new Order(orderID, customerID, totalPrice, itemID, quantity));
		return order;

	}

	@Override
	public void delete() {
		LOGGER.info("please enter the ID of the order you wish to delete");
		Long orderID = Long.valueOf(getInput());
		orderService.delete(orderID);
		LOGGER.info("order deleted");
	}

}
