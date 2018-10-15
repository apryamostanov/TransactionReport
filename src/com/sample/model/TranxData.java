package com.sample.model;

import java.util.Date;


public class TranxData {
	private String id;
	private Date date;
	private double amount;
	private String merchant;
	private boolean paymentTranx;
	private String relationTranx;
	public TranxData(String id, Date date, double amount, String merchant,
			boolean paymentTranx, String relationTranx) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.merchant = merchant;
		this.paymentTranx = paymentTranx;
		this.relationTranx = relationTranx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public boolean isPaymentTranx() {
		return paymentTranx;
	}
	public void setPaymentTranx(boolean paymentTranx) {
		this.paymentTranx = paymentTranx;
	}
	public String getRelationTranx() {
		return relationTranx;
	}
	public void setRelationTranx(String relationTranx) {
		this.relationTranx = relationTranx;
	}
	
	
	
}
