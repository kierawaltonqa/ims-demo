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

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	/*
	 * aiming to have create method which creates order and orderline entries
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

//		String answer = "yes";
//		while (answer.equalsIgnoreCase("yes")) {
//			LOGGER.info("enter the orderlineID corresponding to the item you want to update in this order");
//			Long orderlineID = Long.valueOf(getInput());
//			LOGGER.info("enter the itemID of the item you wish to add to this updated order");
//			Long itemID = Long.valueOf(getInput());
//			LOGGER.info("enter the quantity of this item you would like to add");
//			Integer quantity = Integer.parseInt(getInput());
//			Orderline orderline = orderlineService
//					.update(new Orderline(order.getOrderID(), itemID, quantity, orderlineID));
//			LOGGER.info("enter yes to add more items, enter no to finish order");
//			answer = getInput();
//		
//		LOGGER.info("order updated");
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
