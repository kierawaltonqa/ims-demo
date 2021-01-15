package com.qa.ims.persistence.dao;

public class Practice {

//	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);
//
//	private String jdbcConnectionUrl;
//	private String username;
//	private String password;
//
//	public OrderDaoMysql(String username, String password) {
//		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
//		this.username = username;
//		this.password = password;
//	}
//
//	public OrderDaoMysql(String jdbcConnectionUrl, String username, String password) {
//		super();
//		this.jdbcConnectionUrl = jdbcConnectionUrl;
//		this.username = username;
//		this.password = password;
//	}
//
//	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
//		Long orderID = resultSet.getLong("orderID");
//		Long customerID = resultSet.getLong("customerID");
//		int totalPrice = resultSet.getInt("totalPrice");
//		int quantity = resultSet.getInt("quantity");
//		Long itemID = resultSet.getLong("itemID");
//		return new Order(orderID, customerID, totalPrice);
//
//	public Long getCurrentOrderID() {
//		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
//				Statement statement = connection.createStatement();
//				ResultSet resultSet = statement
//						.executeQuery("SELECT orderID FROM orders ORDER BY orderID "
//								+ "DESC LIMIT 1;");) {
//			resultSet.next();
//			return resultSet.getLong("orderID");
//		} catch (Exception e) {
//			LOGGER.debug(e.getStackTrace());
//			LOGGER.error(e.getStackTrace());
//		}
//		return null;
//	}
//
//	public Order readLatest() {
//		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
//				Statement statement = connection.createStatement();
//				ResultSet resultSet = statement.executeQuery("select * from orders ORDER BY"
//						+ " orderID desc limit 1;");) {
//			resultSet.next();
//			return orderFromResultSet(resultSet);
//		} catch (Exception e) {
//			LOGGER.debug(e.getStackTrace());
//			LOGGER.error(e.getStackTrace());
//		}
//		return null;
//	}
//	public Order create(Order order) {
//		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
//				Statement statement1 = connection.createStatement(); 
//				Statement statement2 = connection.createStatement();) {
//			statement1.executeUpdate("INSERT INTO orders(customerID, totalPrice) VALUES('" 
//			 + order.getCustomerID()
//					+ "', '" + order.getTotalPrice() + "')");
//			 statement2.executeUpdate("insert into orderline(itemID,orderID,quantity)"
//			 + values("' + order.getItemID()" + "','" + getCurrentOrderID() + "','" 
//			 + order.getQuantity() + "')");
//			return readLatest();
//		} catch (Exception e) {
//			LOGGER.debug(e.getStackTrace());
//			LOGGER.error(e.getStackTrace());
//		}
//		return null;
//	}
}
