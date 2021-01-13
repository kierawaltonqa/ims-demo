package com.qa.ims.persistence.domain;

import java.util.List;

public class Order {
	// This class will contain attributes from both the orders and orderline table
	// they are in two distinct tables to handle many-many relations, but the user
	// would enter
	// the details contained in both tables when they are creating an order

	private Long orderID;
	private Long customerID;
	private double totalPrice;
	private List<Long> orderItems;
	private int quantity;

	@Override
	public String toString() {
		return "orderID: " + orderID + ", customerID: " + customerID + ", orderItems: " + orderItems + ", quantity: "
				+ quantity;
	}

	public Order(List<Long> orderitems) {
		super();
		this.orderItems = orderitems;
	}

	// use this in update method in orderController
	public Order(Long orderID, Long customerID, List<Long> orderItems, int quantity) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.orderItems = orderItems;
		this.quantity = quantity;
	}

	public Order(Long orderID, Long customerID, double totalPrice, List<Long> orderItems, int quantity) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.totalPrice = totalPrice;
		this.orderItems = orderItems;
		this.quantity = quantity;
	}

	public Order(Long customerID, List<Long> orderItems, int quantity) {
		super();
		this.customerID = customerID;
		this.orderItems = orderItems;
		this.quantity = quantity;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Long> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Long> orderItems) {
		this.orderItems = orderItems;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + quantity;
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
			return false;
		return true;
	}

}