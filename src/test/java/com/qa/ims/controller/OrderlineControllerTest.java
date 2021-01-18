package com.qa.ims.controller;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.services.OrderlineServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderlineControllerTest {

	@Mock
	private OrderlineServices orderlineServices;

	@Spy
	@InjectMocks
	private OrderlineController orderlineController;
	
	@Test

}
