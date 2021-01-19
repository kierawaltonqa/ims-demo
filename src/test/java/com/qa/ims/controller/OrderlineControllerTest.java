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

import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.services.OrderlineServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderlineControllerTest {

	@Mock
	private OrderlineServices orderlineServices;

	@Spy
	@InjectMocks
	private OrderlineController orderlineController;

	@Test
	public void readAllTest() {
		OrderlineController orderlineController = new OrderlineController(orderlineServices);
		List<Orderline> ols = new ArrayList<>();
		ols.add(new Orderline(1L, 1L, 3));
		ols.add(new Orderline(2L, 1L, 1));
		ols.add(new Orderline(3L, 2L, 2));
		ols.add(new Orderline(4L, 2L, 1));
		Mockito.when(orderlineServices.readAll()).thenReturn(ols);
		assertEquals(ols, orderlineController.readAll());
	}

	@Test
	public void createTest() {
		String orderID = "1";
		String itemID = "1";
		Integer quantity = 3;
		Mockito.doReturn(orderID, itemID, quantity.toString()).when(orderlineController).getInput();
		Orderline ol = new Orderline(1L, 1L, quantity);
		Orderline savedOl = new Orderline(1L, 1L, quantity, 1L);
		Mockito.when(orderlineServices.create(ol)).thenReturn(savedOl);
		assertEquals(savedOl, orderlineController.create());
	}

	@Test
	public void updateTest() {
		Long orderlineID = 1L;
		Long orderID = 1L;
		Long itemID = 1L;
		String quantity = "3";
		Mockito.doReturn(orderID, itemID, orderlineID).when(orderlineController).getLongInput();
		Mockito.doReturn(quantity).when(orderlineController).getInput();
		Orderline ol = new Orderline(1L, 1L, 3, 1L);
		Mockito.when(orderlineServices.update(ol)).thenReturn(ol);
		assertEquals(ol, orderlineController.update());
	}

	@Test
	public void deleteTest() {
		// doesn't return anything - just verifying that it calls the delete method
		String orderlineID = "1";
		Mockito.doReturn(orderlineID).when(orderlineController).getInput();
		orderlineController.delete();
		Mockito.verify(orderlineServices, Mockito.times(1)).delete(1L);
	}

}
