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

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.services.OrderServices;
import com.qa.ims.services.OrderlineServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private OrderServices orderServices;
	private OrderlineServices orderlineServices;

	@Spy
	@InjectMocks
	private OrderController orderController;
	// private OrderlineController orderlineController;

	@Test
	public void readAllOrderTest() {
		OrderController orderController = new OrderController(orderServices, orderlineServices);
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 20.0)); // customerID and price (orderID auto)
		orders.add(new Order(3L, 50.0));
		orders.add(new Order(2L, 20.0));
		Mockito.when(orderServices.readAll()).thenReturn(orders);
		assertEquals(orders, orderServices.readAll());
	}

	@Test
	public void readAllOrderlineTest() {
		OrderController orderController = new OrderController(orderServices, orderlineServices);
		List<Orderline> ols = new ArrayList<>();
		ols.add(new Orderline(1L, 1L, 3));
		ols.add(new Orderline(2L, 1L, 1));
		ols.add(new Orderline(3L, 2L, 2));
		ols.add(new Orderline(4L, 2L, 1));
		Mockito.when(orderlineServices.readAll()).thenReturn(ols);
		assertEquals(ols, orderlineServices.readAll());
	}

	@Test
	public void deleteTest() {
		String orderID = "1";
		Mockito.doReturn(orderID).when(orderController).getInput();
		orderController.delete();
		Mockito.verify(orderServices, Mockito.times(1)).delete(1L);
	}

	@Test
	public void createOrderTest() {
		String customerID = "1";
		Double totalPrice = 50.0;
		Mockito.doReturn(customerID).when(orderController).getInput();
		Mockito.doReturn(totalPrice).when(orderController).getDoubleInput();
		Order order = new Order(1L, 50.0);
		Order savedOrder = new Order(1L, 1L, 50.0);
		Mockito.when(orderServices.create(order)).thenReturn(savedOrder);
		assertEquals(savedOrder, orderController.create());
	}

	@Test
	public void createOrderlineCaseATest() {

	}

	@Test
	public void updateOrderlineCaseBTest() {

	}

	@Test
	public void removeOrderlineCaseBTest() {

	}

	@Test
	public void updateTest() {
		String orderID = "1";
		String customerID = "1";
		Double totalPrice = 50.0;
		Mockito.doReturn(orderID, customerID, totalPrice.toString()).when(orderController).getInput();
		Order order = new Order(1L, 1L, totalPrice);
		Mockito.when(orderServices.update(order)).thenReturn(order);
		assertEquals(order, orderController.update());
	}

}
