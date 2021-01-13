package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		String orderDate = resultSet.getString("orderDate");
		// Long orderlineID = resultSet.getLong("orderlineID");
		List<Long> orderItems = (List<Long>) resultSet.getArray("orderItems"); // unchecked casting warning ??
		List<Integer> quantity = (List<Integer>) resultSet.getArray("quantity");
		return new Order(orderID, customerID, orderDate, orderItems, quantity);
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orderline ol JOIN orders o ON ol.orderID=o.orderID");) {
			// used the join method here to combine data from orders table with data from
			// orderline table
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

	public Order readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orderline ol JOIN orders o ON ol.orderID=o.orderID"
								+ " ORDER BY o.orderID DESC LIMIT 1;");) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return null;
	}

	@Override
	public Order create(Order order) {
		String orderlinequery = "INSERT INTO orderline(itemID,orderID,quantity VALUES(?, ?, ?)";
		String IDquery = "SELECT orderID FROM orders ORDER BY orderID DESC LIMIT 1";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement1 = connection.createStatement();
				PreparedStatement statement2 = connection.prepareStatement(orderlinequery);
				PreparedStatement statement3 = connection.prepareStatement(IDquery);) {
			statement1.executeUpdate("INSERT INTO orders(customerID,orderDate) VALUES('" + order.getCustomerID()
					+ "', '" + order.getOrderDate() + "';");

			try (ResultSet resultset = statement3.executeQuery();) {
				resultset.next();
				Long thisorderID = resultset.getLong("orderID");
				List<Long> itemIDs = order.getOrderitems();
				List<Integer> quantity = order.getQuantity();
				for (Long ID : itemIDs) {
					statement2.setString(1, "" + ID);
					statement2.setString(2, "" + thisorderID);
					statement2.setString(3, "" + quantity.get(itemIDs.indexOf(ID)));
				}
				resultset.next();
				return readLatest();
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getStackTrace());
			}

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

//	@Override
//	public Order update(Order order) {
//		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
//				Statement statement1 = connection.createStatement();
//				Statement statement2 = connection.createStatement();) {
//			statement1.executeUpdate("UPDATE orders SET customerID = '" + order.getCustomerID() + ", orderDate = '"
//					+ order.getOrderDate() + "';");
//			statement2.executeUpdate("UPDATE orderline SET ");
//
//		}
//
//	}

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

	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

}
