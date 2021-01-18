package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Orderline;

public class OrderlineDaoMysql implements Dao<Orderline> {

	public static final Logger LOGGER = Logger.getLogger(OrderlineDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderlineDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
		this.username = username;
		this.password = password;
	}

	public OrderlineDaoMysql(String jdbcConnectionUrl, String username, String password) {
		super();
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	Orderline orderlineFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderID = resultSet.getLong("orderID");
		Long itemID = resultSet.getLong("itemID");
		int quantity = resultSet.getInt("quantity");
		Long orderlineID = resultSet.getLong("orderlineID");
		return new Orderline(orderID, itemID, quantity, orderlineID);
	}

	@Override
	public List<Orderline> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderline");) {
			ArrayList<Orderline> orderlines = new ArrayList<>();
			while (resultSet.next()) {
				orderlines.add(orderlineFromResultSet(resultSet));
			}
			return orderlines;
		} catch (SQLException e) {
			LOGGER.debug(e.getSQLState());
			LOGGER.debug(e.getStackTrace());
		}
		return new ArrayList<>();
	}

	public Orderline readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orderline ORDER BY orderlineID DESC LIMIT 1");) {
			resultSet.next();
			return orderlineFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return null;
	}

	@Override
	public Orderline create(Orderline orderline) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orderline(orderID,itemID,quantity) VALUES('" + orderline.getOrderID()
					+ "','" + orderline.getItemID() + "','" + orderline.getQuantity() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return null;
	}

	public Orderline readOrderline(Long orderlineID) {

		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement
					.executeQuery("SELECT FROM orderline WHERE orderlineID='" + orderlineID + "';");
			return orderlineFromResultSet(resultSet);

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
		return null;

	}

	@Override
	public Orderline update(Orderline orderline) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("UPDATE orderline SET itemID = '" + orderline.getItemID() + "', orderID = '"
					+ orderline.getOrderID() + "', quantity = '" + orderline.getQuantity() + "' WHERE orderlineID = '"
					+ orderline.getOrderlineID() + "');");
			return readOrderline(orderline.getOrderID());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM orderline WHERE orderlineID = '" + id + ",;");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

	}

}
