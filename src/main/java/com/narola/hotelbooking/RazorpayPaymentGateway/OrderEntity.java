package com.narola.hotelbooking.RazorpayPaymentGateway;

public class OrderEntity {
	
	private int amount;
	private String currency;
	private String receipt;
	
	public void setAmount(int amount) {
		this.amount = amount;
	}	
	public int getAmount() {
		return amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
}
