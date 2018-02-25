package com.soaringclouds.product.model;

public class CurrencyDO {
	private CurrencyEnum currency;
	private String currencyName;
	private Double exchangeRate;
	
	public CurrencyDO (CurrencyEnum currency, String currencyName, Double exchangeRate) {
		this.currency = currency;
		this.currencyName = currencyName;
		this.exchangeRate = exchangeRate;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	@Override
	public String toString() {
		return "CurrencyDO [currency=" + currency + ", currencyName=" + currencyName + ", exchangeRate=" + exchangeRate
				+ "]";
	}

}
