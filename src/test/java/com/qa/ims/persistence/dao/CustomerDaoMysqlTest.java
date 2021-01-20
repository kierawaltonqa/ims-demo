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
import com.qa.ims.persistence.domain.Customer;

public class CustomerDaoMysqlTest {

	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);

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
			statement.executeUpdate(
					"delete * from customers; insert into customers(first_name,surname) values('kiera','walton');");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getStackTrace());
		}
	}

	@Test
	public void createTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(jdbcConnectionUrl, username, password);
		String fn = "jim";
		String ln = "bob";
		Customer customer = new Customer(2L, fn, ln);
		Customer savedCustomer = new Customer(2L, fn, ln);
		customer = customerDaoMysql.create(customer);
		assertEquals(savedCustomer, customer);
	}

	@Test
	public void deleteTest() {
		// delete from customers where Id equals something
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(jdbcConnectionUrl, username, password);
		String fn = "kiera";
		String ln = "walton";
		Customer customer = new Customer(1L, fn, ln);
		Customer savedCustomer = new Customer(1L, fn, ln);
		customer.setId(null);
		customerDaoMysql.delete(1L);
		assertNotEquals(customer, savedCustomer);

	}

	@Test
	public void readTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(jdbcConnectionUrl, username, password);
		String fn = "kiera";
		String ln = "walton";
		Customer customer = new Customer(1L, fn, ln);
		Customer savedCustomer = new Customer(1L, fn, ln);
		customer.setId(1L);
		customerDaoMysql.readCustomer(1L);
		assertEquals(savedCustomer, customer);
	}

	@Test
	public void updateTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(jdbcConnectionUrl, username, password);
		String fn = "kiera";
		String ln = "walton";
		Customer savedCustomer = new Customer(1L, fn, ln);
		Customer customer = new Customer(1L, fn, ln);
		customerDaoMysql.update(customer);
		customer.setId(1L);
		assertEquals(savedCustomer, customer);
	}

	@Test
	public void readAllTest() {
		List<Customer> customers = new ArrayList<>();
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(jdbcConnectionUrl, username, password);
		String fn = "kiera";
		String ln = "walton";
		Customer customer = new Customer(1L, fn, ln);
		Customer savedCustomer = new Customer(1L, fn, ln);
		customers.add(customer);
		customers.add(savedCustomer);
		assertEquals(savedCustomer, customer);

	}
}