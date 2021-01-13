package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	// create a logger
	public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	// create an attribute crudServices<Order>
	private CrudServices<Order> orderService;

	// create a generator for orderService

	public OrderController(CrudServices<Order> orderService) {
		super();
		this.orderService = orderService;
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

//this create method fails when it hits the while loop at line 48 - sort this tomorrow!!
	@Override
	public Order create() {
		int quantity = 0;
		ArrayList<Long> orderItems = new ArrayList<>();
		LOGGER.info("enter the customerID of the order you wish to create");
		Long customerID = Long.valueOf(getInput());
		LOGGER.info("enter 'yes' to add your first item to this order");
		while (getInput() == "yes") {
			LOGGER.info("enter the ID of an item you wish to add to your order");
			Long itemID = Long.valueOf(getInput());
			orderItems.add(itemID);
			LOGGER.info("enter the quantity of this item you wish to add to the order");
			quantity = Integer.parseInt(getInput());
			LOGGER.info("enter 'yes' to add another item, enter 'no' to finish order");
		}
		Order order = orderService.create(new Order(customerID, orderItems, quantity));
		return order;
	}
	/*
	 * in this update method, you can update the order by referring to the customer
	 * first name and surname, then the customerID will be found and the order which
	 * corresponds to that ID and date will be found
	 */

	@Override
	public Order update() {
		int quantity = 0;
		ArrayList<Long> orderItems = new ArrayList<>();
		LOGGER.info("enter the ID of the order you wish to update");
		Long orderID = Long.valueOf(getInput());
		LOGGER.info("enter the ID of the customer corresponding to the order");
		Long customerID = Long.valueOf(getInput());
		LOGGER.info("enter 'yes' to add your first item to this order");
		while (getInput() == "yes") {
			LOGGER.info("enter the ID of an item you wish to add to the order");
			Long itemID = Long.valueOf(getInput());
			orderItems.add(itemID);
			LOGGER.info("enter the quantity of this item you wish to add");
			quantity = Integer.parseInt(getInput());
			LOGGER.info("enter 'yes' to add more items, and 'no' to complete order");
		}
		Order order = orderService.create(new Order(orderID, customerID, orderItems, quantity));
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
