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
import com.qa.ims.persistence.domain.Item;

public class ItemDaoMysqlTest {

	public static final Logger LOGGER = Logger.getLogger(ItemDaoMysql.class);

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
			statement.executeUpdate("delete from items;");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
	}

	@Test
	public void createTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(jdbcConnectionUrl, username, password);
		String itemName = "candle";
		Double itemPrice = 5.0;
		Item item = new Item(itemName, itemPrice);
		Item savedItem = new Item(1L, itemName, itemPrice);
		item = itemDaoMysql.create(item);
		assertEquals(savedItem, item);
	}

	@Test
	public void readAllTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(jdbcConnectionUrl, username, password);
		List<Item> items = new ArrayList<>();
		String itemName = "candle";
		Double itemPrice = 5.0;
		Item item = new Item(1L, itemName, itemPrice);
		Item savedItem = new Item(1L, itemName, itemPrice);
		items.add(item);
		items.add(savedItem);
		assertEquals(savedItem, item);
	}

	@Test
	public void readTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(jdbcConnectionUrl, username, password);
		String itemName = "candle";
		Double itemPrice = 5.0;
		Item item = new Item(1L, itemName, itemPrice);
		Item savedItem = new Item(1L, itemName, itemPrice);
		item.setItemID(1L);
		itemDaoMysql.readItem(1L);
		assertEquals(savedItem, item);
	}

	@Test
	public void deleteTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(jdbcConnectionUrl, username, password);
		String itemName = "candle";
		Double itemPrice = 5.0;
		Item item = new Item(1L, itemName, itemPrice);
		Item savedItem = new Item(1L, itemName, itemPrice);
		item.setItemID(null);
		itemDaoMysql.delete(1L);
		assertNotEquals(savedItem, item);
	}

	@Test
	public void updateTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(jdbcConnectionUrl, username, password);
		String itemName = "candle";
		Double itemPrice = 5.0;
		Item item = new Item(1L, itemName, itemPrice);
		Item savedItem = new Item(1L, itemName, itemPrice);
		itemDaoMysql.update(item);
		assertEquals(savedItem, item);
	}

}