package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderlineController implements CrudController<Orderline> {

	public static final Logger LOGGER = Logger.getLogger(OrderlineController.class);

	private CrudServices<Orderline> orderlineService;

	public OrderlineController(CrudServices<Orderline> orderlineService) {
		super();
		this.orderlineService = orderlineService;
	}

	String getInput() {
		return Utils.getInput();
	}

	@Override
	public List<Orderline> readAll() {
		List<Orderline> orderlines = orderlineService.readAll();
		for (Orderline orderline : orderlines) {
			LOGGER.info(orderline.toString());
		}
		return orderlines;
	}

	@Override
	public Orderline create() {
		LOGGER.info("please enter the orderID");
		Long orderID = Long.valueOf(getInput());
		LOGGER.info("please enter the itemID");
		Long itemID = Long.valueOf(getInput());
		LOGGER.info("please enter the quantity of the item in the order");
		int quantity = Integer.parseInt(getInput());
		Orderline orderline = orderlineService.create(new Orderline(orderID, itemID, quantity));
		LOGGER.info("orderline record created");
		return orderline;
	}

	@Override
	public Orderline update() {
		LOGGER.info("please enter the ID of the order you wish to update");
		Long orderID = Long.valueOf(getInput());
		LOGGER.info("please enter the ID of the item you wish to add");
		Long itemID = Long.valueOf(getInput());
		LOGGER.info("please enter the quantity of this item you want to add");
		int quantity = Integer.parseInt(getInput());
		Orderline orderline = orderlineService.create(new Orderline(orderID, itemID, quantity));
		LOGGER.info("this order has been recorded in the orderline!");
		return orderline;
	}

	@Override
	public void delete() {
		LOGGER.info("please enter the ID of the order you wish to delete from the orderline");
		Long orderID = Long.valueOf(getInput());
		orderlineService.delete(orderID);
		LOGGER.info("order deleted from orderline");

	}

}
