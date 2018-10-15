package com.sample.model;

public class Result {
	private int noOfTranx;
	private double averageTranxValue;
	
	public Result(int noOfTranx, double averageTranxValue) {
		this.noOfTranx = noOfTranx;
		this.averageTranxValue = averageTranxValue;
	}
	
	public int getNoOfTranx() {
		return noOfTranx;
	}
	public void setNoOfTranx(int noOfTranx) {
		this.noOfTranx = noOfTranx;
	}
	public double getAverageTranxValue() {
		return averageTranxValue;
	}
	public void setAverageTranxValue(double averageTranxValue) {
		this.averageTranxValue = averageTranxValue;
	}

	@Override
	public String toString() {
		return "Result [noOfTranx=" + noOfTranx + ", averageTranxValue="
				+ averageTranxValue + "]";
	}
	
	
}
