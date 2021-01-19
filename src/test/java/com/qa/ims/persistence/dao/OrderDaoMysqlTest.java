package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.Order;

public class OrderDaoMysqlTest {

	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);

	private static String jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims_test";
	private static String username = "root";
	private static String password = "root";

	@BeforeClass
	public static void init() {
		Ims ims = new Ims();
		ims.init(jdbcConnectionUrl, username, password, "src/test/resources/sql-schema.sql");
	}

	@Before
	public void setUp() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orders;");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
	}

	@Test
	public void createTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(jdbcConnectionUrl, username, password);
		Long customerID = 1L;
		Double totalPrice = 50.0;
		Order order = new Order(customerID, totalPrice);
		Order savedOrder = new Order(1L, customerID, totalPrice);
		order = orderDaoMysql.create(order);
		assertEquals(savedOrder, order);
	}

	@Test
	public void readAllTest() {
		List<Order> orders = new ArrayList<>();
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(jdbcConnectionUrl, username, password);
		Long customerID = 1L;
		Double totalPrice = 50.0;
		Order order = new Order(1L, customerID, totalPrice);
		Order savedOrder = new Order(1L, customerID, totalPrice);
		orders.add(order);
		orders.add(savedOrder);
		assertEquals(order, savedOrder);
	}

	@Test
	public void readOrderTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(jdbcConnectionUrl, username, password);
		Long customerID = 1L;
		Double totalPrice = 50.0;
		Order order = new Order(1L, customerID, totalPrice);
		Order savedOrder = new Order(1L, customerID, totalPrice);
		order.setOrderID(1L);
		orderDaoMysql.readOrder(1L);
		assertEquals(savedOrder, order);
	}

	public void updateTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(jdbcConnectionUrl, username, password);
		Long customerID = 1L;
		Double totalPrice = 50.0;
		Order order = new Order(1L, customerID, totalPrice);
		Order savedOrder = new Order(1L, customerID, totalPrice);
		orderDaoMysql.update(order);
		order.setOrderID(1L);
		assertEquals(savedOrder, order);
	}

	@Test
	public void deleteTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(jdbcConnectionUrl, username, password);
		Long customerID = 1L;
		Double totalPrice = 50.0;
		Order order = new Order(1L, customerID, totalPrice);
		Order savedOrder = new Order(1L, customerID, totalPrice);
		order.setOrderID(null);
		orderDaoMysql.delete(1L);
		assertNotEquals(order, savedOrder);
	}
}
