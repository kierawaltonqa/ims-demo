package com.qa.ims.persistence.dao;

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

	public OrderDaoMysql() {
		super();
	}

//	public Long getcustomerID(String fname, String sname) {
//		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
//				Statement statement = connection.createStatement();
//				ResultSet resultSet = statement.executeQuery("SELECT id FROM customers WHERE first_name = '" + fname
//						+ "' and surname = '" + sname + "';");) {
//			Long customerID = resultSet.getLong("id");
//			return customerID;
//		} catch (Exception e) {
//			LOGGER.debug(e.getStackTrace());
//			LOGGER.error(e.getStackTrace());
//		}
//		return getcustomerID(fname, sname);
//	}

	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order create(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
