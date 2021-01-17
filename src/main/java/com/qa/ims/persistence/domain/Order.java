package com.qa.ims.persistence.domain;

public class Order {

	private Long orderID;
	private Long customerID;
	private int totalPrice;
//added these two attributes
//	private Long itemID;
//	private int quantity;
//	private List<Long> items;

	public Order(Long customerID, int totalPrice) {
		super();
		this.customerID = customerID;
		this.totalPrice = totalPrice;
	}

	public Order(Long orderID, Long customerID, int totalPrice) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.totalPrice = totalPrice;
	}

//	public Order(Long orderID, Long customerID, int totalPrice, Long itemID, int quantity, List<Long> items) {
//		super();
//		this.orderID = orderID;
//		this.customerID = customerID;
//		this.totalPrice = totalPrice;
//		this.itemID = itemID;
//		this.quantity = quantity;
//		this.items = items;
//	}

	@Override
	public String toString() {
		return "orderID: " + orderID + ", customerID: " + customerID + ", totalPrice: " + totalPrice;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
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
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (totalPrice != other.totalPrice)
			return false;
		return true;
	}

}
