package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;

public class OrderDaoMysql implements Dao<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
		this.username = username;
		this.password = password;
	}

	public OrderDaoMysql(String jdbcConnectionUrl, String username, String password) {
		super();
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderID = resultSet.getLong("orderID");
		Long customerID = resultSet.getLong("customerID");
		int totalPrice = resultSet.getInt("totalPrice");
		int quantity = resultSet.getInt("quantity");
		Long itemID = resultSet.getLong("itemID");
		return new Order(orderID, customerID, totalPrice, itemID, quantity);
	}
//	public Order itemPriceCalc(Order order) {
//		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
//				Statement statement = connection.createStatement();
//				ResultSet resultSet = statement
//						.executeQuery("SELECT itemPrice FROM items WHERE itemID= '" + order.getItemID() + "';");) {
//			resultSet.next();
//			return orderFromResultSet(resultSet);
//		} catch (Exception e) {
//			LOGGER.debug(e.getStackTrace());
//			LOGGER.error(e.getStackTrace());
//		}
//		return null;
//	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orderline ol JOIN orders o ON ol.orderID=o.orderID");) {
			ArrayList<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(orderFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return new ArrayList<>();
	}

//method to get the current ID in an order (used in the create method)
	public Long getCurrentOrderID() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("select orderID from orders ORDER BY orderID desc limit 1;");) {
			resultSet.next();
			return resultSet.getLong("orderID");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return null;
	}

	public Order readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders ORDER BY orderID desc limit 1;");) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return null;
	}

	@Override
//	public Order create(Order order) {
//		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
//				Statement statement1 = connection.createStatement();) {
//			statement1.executeUpdate("insert into orders(customerID, totalPrice) values('" + order.getCustomerID()
//					+ "', '" + order.getTotalPrice() + "')");
//		} catch (Exception e) {
//			LOGGER.debug(e.getStackTrace());
//			LOGGER.error(e.getStackTrace());
//		}
//		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
//				Statement statement2 = connection.createStatement();) {
//			statement2.executeUpdate("insert into orderline(itemID,orderID,quantity) values('" + order.getOrderID()
//					+ "','" + order.getOrderID() + "','" + order.getQuantity() + "')");
//			return readLatest2();
//		} catch (Exception e) {
//			LOGGER.debug(e.getStackTrace());
//			LOGGER.error(e.getStackTrace());
//		}
//		return null;
//	}

//	}
	public Order create(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement1 = connection.createStatement();
				Statement statement2 = connection.createStatement();) {
			statement1.executeUpdate("INSERT INTO orders(customerID, totalPrice) VALUES('" + order.getCustomerID()
					+ "', '" + order.getTotalPrice() + "')");
			statement2.executeUpdate("insert into orderline(itemID,orderID,quantity) values('" + order.getItemID()
					+ "','" + getCurrentOrderID() + "','" + order.getQuantity() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return null;
	}

	public Order readOrder(Long orderID) {

		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery("SELECT FROM orders WHERE orderID='" + orderID + "';");
			return orderFromResultSet(resultSet);

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return null;

	}

	@Override
	public Order update(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement1 = connection.createStatement();
				Statement statement2 = connection.createStatement();) {
			statement1.executeUpdate("UPDATE orders SET customerID = '" + order.getCustomerID() + "', totalPrice = '"
					+ order.getTotalPrice() + ", WHERE orderID ='" + order.getOrderID() + "';");
			statement2.executeUpdate("UPDATE orderline SET itemID= '" + order.getItemID() + "', quantity = '"
					+ order.getQuantity() + "' WHERE orderID = '" + order.getOrderID() + "';");
			return readOrder(order.getOrderID());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.info(e.getStackTrace());
		}
		return null;
	}

	@Override
	public void delete(long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM orders WHERE orderID = '" + id + "';");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.debug(e.getStackTrace());
		}

	}

}
