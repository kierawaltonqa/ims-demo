package com.qa.ims.persistence.domain;

public class Order {
	// This class will contain attributes from both the orders and orderline table
	// they are in two distinct tables to handle many-many relations, but the user
	// would enter
	// the details contained in both tables when they are creating an order

	private Long orderID;
	private Long customerID;
	private int totalPrice;
	private Long itemID;
	private int quantity;

	public Order(Long itemID) {
		this.itemID = itemID;
	}

	public Order(Long customerID, int totalPrice) {
		super();
		this.customerID = customerID;
		this.totalPrice = totalPrice;
	}

	// use this in update method in orderController
	public Order(Long orderID, Long customerID, Long itemID, int quantity) {
		this.orderID = orderID;
		this.customerID = customerID;
		this.itemID = itemID;
		this.quantity = quantity;
	}

	public Order(Long orderID, Long customerID, int totalPrice, Long itemID, int quantity) {
		this.orderID = orderID;
		this.customerID = customerID;
		this.totalPrice = totalPrice;
		this.itemID = itemID;
		this.quantity = quantity;
	}

	public Order(Long customerID, Long itemID, int quantity) {
		this.customerID = customerID;
		this.itemID = itemID;
		this.quantity = quantity;
	}

	public Order(Long customerID, int totalPrice, Long itemID, int quantity) {
		this.customerID = customerID;
		this.totalPrice = totalPrice;
		this.itemID = itemID;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "orderID: " + orderID + ", customerID: " + customerID + ", totalPrice: " + totalPrice + ", itemID: "
				+ itemID + ", quantity: " + quantity;
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

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
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
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result + quantity;
		result = prime * result + totalPrice;
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
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (quantity != other.quantity)
			return false;
		if (totalPrice != other.totalPrice)
			return false;
		return true;
	}

}