package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	private CrudServices<Order> orderService;
	private CrudServices<Orderline> orderlineService;

	public OrderController(CrudServices<Order> orderService) {
		super();
		this.orderService = orderService;
	}

	public OrderController(CrudServices<Order> orderService, CrudServices<Orderline> orderlineService) {
		super();
		this.orderService = orderService;
		this.orderlineService = orderlineService;
	}

	String getInput() {
		return Utils.getInput();
	}

//TRY TO FIND WAY TO PRINT OUT ORDERLINE DETAILS TOO??
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		LOGGER.info("note: orderline details can only be read when required (i.e. when updating an order)");
		return orders;
	}

	/*
	 * create method which creates the order and the orderline entries
	 */
	@Override
	public Order create() {
		LOGGER.info("enter the customerID for the order you wish to create");
		Long customerID = Long.valueOf(getInput());
		LOGGER.info("enter the total price of your order");
		Double totalPrice = Double.valueOf(getInput());
		Order order = orderService.create(new Order(customerID, totalPrice));
		LOGGER.info("order created, with orderID: " + order.getOrderID());

		String answer = "yes";
		while (answer.equalsIgnoreCase("yes")) {
			LOGGER.info("enter the itemID of the item you wish to add");
			Long itemID = Long.valueOf(getInput());
			LOGGER.info("enter the quantity of this item you would like to add");
			Integer quantity = Integer.parseInt(getInput());
			Orderline orderline = orderlineService.create(new Orderline(order.getOrderID(), itemID, quantity));
			LOGGER.info("enter yes to add more items, enter no to finish order");
			answer = getInput();
		}
		LOGGER.info("order created");
		return order;
	}

	@Override
	public Order update() {

		LOGGER.info("enter the ID of the order you wish to update");
		Long orderID = Long.valueOf(getInput());
		LOGGER.info("now you need to enter the updated details of this order");
		LOGGER.info("enter the ID of the customer corresponding to this order");
		Long customerID = Long.valueOf(getInput());
		LOGGER.info("enter the total price of this order");
		Double totalPrice = Double.valueOf(getInput());
		Order order = orderService.update(new Order(orderID, customerID, totalPrice));
		LOGGER.info("order updated");
		LOGGER.info("time to record the details of this updated order");

		String answer = "yes";
		while (answer.equalsIgnoreCase("yes")) {
			LOGGER.info(
					"select A to add items, B to remove items, or C to change the quantity of an item from this order");
			String updateChoice = getInput();
			switch (updateChoice) {
			case "A":
				LOGGER.info("enter the itemID of the item you wish to add");
				Long itemID = Long.valueOf(getInput());
				LOGGER.info("enter the quantity of this item you wish to add to the order");
				Integer quantity = Integer.parseInt(getInput());
				Orderline orderline = orderlineService.create(new Orderline(orderID, itemID, quantity));
				LOGGER.info("item added");
				break;

			case "B":
				List<Orderline> availableOrderlines = orderlineService.readAll();
				for (Orderline ol : availableOrderlines) {
					if (ol.getOrderID().equals(orderID)) {
						LOGGER.info("available orderlineID: " + ol.getOrderlineID());
					}
				}
				LOGGER.info("select the orderlineID from the list above of the item you wish to remove");
				Long orderlineID = Long.valueOf(getInput());
				orderlineService.delete(orderlineID);
				LOGGER.info("item removed");
				break;

			case "C":
				List<Orderline> availableOrderlines2 = orderlineService.readAll();
				for (Orderline ol : availableOrderlines2) {
					if (ol.getOrderID().equals(orderID)) {
						LOGGER.info("available orderlineID: " + ol.getOrderlineID() + ", corresponding itemID: "
								+ ol.getItemID());
					}
				}
				LOGGER.info("enter the orderlineID from the list above of the item you wish to update");
				Long orderlineID2 = Long.valueOf(getInput());
				LOGGER.info("enter the itemID corresponding to this orderline entry");
				Long itemID2 = Long.valueOf(getInput());
				LOGGER.info("enter the new quantity of this item");
				Integer quantity2 = Integer.parseInt(getInput());
				Orderline ol = orderlineService.update(new Orderline(orderID, itemID2, quantity2, orderlineID2));
				LOGGER.info("quantity changed");
				break;

			default:
				LOGGER.info("not a valid option");
				break;
			}
			LOGGER.info("enter yes to add, update or remove another item, enter no to quit");
			answer = getInput();
		}
		LOGGER.info("update details recorded");
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
