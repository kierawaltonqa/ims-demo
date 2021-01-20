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
import com.qa.ims.persistence.domain.Orderline;

public class OrderlineDaoMysqlTest {

	public static final Logger LOGGER = Logger.getLogger(OrderlineDaoMysql.class);

	private static String jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims_test";
	private static String username = "root";
	private static String password = "root";

	@BeforeClass
	public static void init() {
		Ims ims = new Ims();
		ims.init(jdbcConnectionUrl, username, password, "src/test/resources/sql-schema.sql");
	}

	@Before
	public void setup() {
		// cleans out database every time
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete * from ims_test.orderline");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
	}

	@Test
	public void createTest() {
		OrderlineDaoMysql orderlineDaoMysql = new OrderlineDaoMysql(jdbcConnectionUrl, username, password);
		Long orderID = 1L;
		Long itemID = 2L;
		Integer quantity = 3;
		Orderline ol = new Orderline(orderID, itemID, quantity);
		Orderline savedOl = new Orderline(orderID, itemID, quantity, 1L);
		ol = orderlineDaoMysql.create(ol);
		ol.setOrderlineID(1L);
		assertEquals(savedOl, ol);
	}

	@Test
	public void deleteTest() {
		OrderlineDaoMysql orderlineDaoMysql = new OrderlineDaoMysql(jdbcConnectionUrl, username, password);
		Long orderID = 1L;
		Long itemID = 2L;
		Integer quantity = 3;
		Orderline ol = new Orderline(orderID, itemID, quantity);
		Orderline savedOl = new Orderline(orderID, itemID, quantity, 1L);
		ol.setOrderlineID(null);
		orderlineDaoMysql.delete(1L);
		assertNotEquals(ol, savedOl);
	}

	@Test
	public void readTest() {
		OrderlineDaoMysql orderlineDaoMysql = new OrderlineDaoMysql(jdbcConnectionUrl, username, password);
		Long orderID = 1L;
		Long itemID = 2L;
		Integer quantity = 3;
		Orderline ol = new Orderline(orderID, itemID, quantity);
		Orderline savedOl = new Orderline(orderID, itemID, quantity, 1L);
		ol.setOrderlineID(1L);
		orderlineDaoMysql.readOrderline(1L);
		assertEquals(savedOl, ol);
	}

	@Test
	public void updateTest() {
		OrderlineDaoMysql orderlineDaoMysql = new OrderlineDaoMysql(jdbcConnectionUrl, username, password);
		Long orderID = 1L;
		Long itemID = 2L;
		Integer quantity = 3;
		Orderline ol = new Orderline(orderID, itemID, quantity);
		Orderline savedOl = new Orderline(orderID, itemID, quantity, 1L);
		orderlineDaoMysql.update(ol);
		ol.setOrderlineID(1L);
		assertEquals(ol, savedOl);
	}

	@Test
	public void readAllTest() {
		List<Orderline> orderlines = new ArrayList<>();
		OrderlineDaoMysql orderlineDaoMysql = new OrderlineDaoMysql(jdbcConnectionUrl, username, password);
		Long orderID = 1L;
		Long itemID = 2L;
		Integer quantity = 3;
		Orderline ol = new Orderline(orderID, itemID, quantity, 1L);
		Orderline savedOl = new Orderline(orderID, itemID, quantity, 1L);
		orderlines.add(ol);
		orderlines.add(savedOl);
		assertEquals(savedOl, ol);
	}

}
