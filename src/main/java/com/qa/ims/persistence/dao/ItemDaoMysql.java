package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Item;

public class ItemDaoMysql implements Dao<Item> {

	public static final Logger LOGGER = Logger.getLogger(ItemDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public ItemDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
		this.username = username;
		this.password = password;
	}

	public ItemDaoMysql(String jdbcConnectionUrl, String username, String password) {
		super();
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	Item itemFromResultSet(ResultSet resultSet) throws SQLException {
		Long itemID = resultSet.getLong("itemID");
		String itemName = resultSet.getString("itemName");
		int itemPrice = resultSet.getInt("itemPrice");
		return new Item(itemID, itemName, itemPrice);

	}

	/*
	 * reads all items from the database and returns a list of those items
	 */

	@Override
	public List<Item> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			ArrayList<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(itemFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Item readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY itemID DESC LIMIT 1");) {
			resultSet.next();
			return itemFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return null;
	}

	@Override
	public Item create(Item item) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO items(itemName,itemPrice) VALUES('" + item.getItemName() + "','"
					+ item.getItemPrice() + "')");
			return readLatest();
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return null;
	}

	public Item readItem(Long itemID) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery("SELECT FROM items WHERE itemID = '" + itemID + "';");
			return itemFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return null;
	}

	@Override
	public Item update(Item item) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("UPDATE items SET itemName = '" + item.getItemName() + "', itemPrice = '"
					+ item.getItemPrice() + "' WHERE itemID = '" + item.getItemID() + "';");
			return readItem(item.getItemID());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return null;
	}

	@Override
	public void delete(long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM items WHERE itemID = '" + id + "';");
			System.out.println("item deleted");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}

	}

}
