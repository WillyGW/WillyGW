package com.study.springmvc.case04.entity;

public class stock {
	private String symbol;
	private Double price;
	private Integer amount;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer stock) {
		this.amount = stock;
	}
	@Override
	public String toString() {
		return "stock [symbol=" + symbol + ", price=" + price + ", stock=" + amount + "]";
	}
	
}
