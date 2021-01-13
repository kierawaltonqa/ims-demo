package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDaoMysql;
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
		this.orderService = orderService;
	}

	String getInput() {
		return Utils.getInput();
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		return orders;

	}

	@Override
	public Order create() {
		LOGGER.info("enter the customerID of the order you wish to create");
		Long customerID = Long.valueOf(getInput());
		LOGGER.info("enter the date when this order was placed");
		String orderDate = getInput();
		Order order = orderService.create(new Order(customerID, orderDate));
		return order;
	}

	@Override
	public Order update() {
		LOGGER.info("enter the first name of the customer whos order you wish to update");
		String first_name = getInput();
		LOGGER.info("enter the last name of the customer whos order you wish to create");
		String surname = getInput();
		// find the customer ID that corresponds to that first name
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("root", "root");
		Long customerID = orderDaoMysql.getcustomerID(first_name, surname);
		LOGGER.info("please enter the date when this order was placed");
		String orderDate = getInput();
		Order order = orderService.create(new Order(customerID, orderDate));
		return order;

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

}
