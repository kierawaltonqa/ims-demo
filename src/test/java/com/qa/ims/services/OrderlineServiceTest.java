package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Orderline;

@RunWith(MockitoJUnitRunner.class)
public class OrderlineServiceTest {

	@Mock
	private Dao<Orderline> orderlineDao;

	@InjectMocks
	private OrderlineServices orderlineServices;

	@Test
	public void orderServicesReadAll() {
		orderlineServices.readAll();
		Mockito.verify(orderlineDao, Mockito.times(1)).readAll();
	}

	@Test
	public void orderServicesDelete() {
		orderlineServices.delete(1L);
		Mockito.verify(orderlineDao, Mockito.times(1)).delete(1L);
	}

	@Test
	public void orderServicesCreate() {
		Orderline ol = new Orderline(1L, 2L, 3);
		orderlineServices.create(ol);
		Mockito.verify(orderlineDao, Mockito.times(1)).create(ol);
	}

	@Test
	public void orderServicesUpdate() {
		Orderline ol = new Orderline(1L, 2L, 3);
		orderlineServices.update(ol);
		Mockito.verify(orderlineDao, Mockito.times(1)).update(ol);
	}

}
