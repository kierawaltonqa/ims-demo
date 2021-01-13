package com.qa.ims.persistence.domain;

public class Order {
	private Long orderID;
	private Long customerID;
	private String orderDate;

	public Order(Long customerID, String orderDate) {
		super();
		this.customerID = customerID;
		this.orderDate = orderDate;
	}

	public Order(Long orderID, Long customerID, String orderDate) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.orderDate = orderDate;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "OrderID: " + orderID + ", customerID: " + customerID + ", orderDate: " + orderDate;
	}

}
