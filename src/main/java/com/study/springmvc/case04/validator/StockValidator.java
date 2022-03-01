package com.study.springmvc.case04.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.study.springmvc.case04.entity.stock;

import yahoofinance.YahooFinance;

@Component
public class StockValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// 驗證
		return stock.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		stock stock = (stock) target;
		ValidationUtils.rejectIfEmpty(errors, "symbol", "stock.symbol.empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "stock.price.empty");
		ValidationUtils.rejectIfEmpty(errors, "amount", "stock.amount.empty");
		
		yahoofinance.Stock yStock = null;
		
		try {
			yStock = YahooFinance.get(stock.getSymbol());
			// 作收
			double previousClose = yStock.getQuote().getPreviousClose().doubleValue();
			
			double price = stock.getPrice();
			int amount = stock.getAmount();
			if(price < previousClose * 0.9 || price > previousClose * 1.1) {
				/*errors.rejectValue("price", "stock.price.range");*/
				errors.rejectValue("price", "stock.price.range",
						new Object[] {(previousClose * 0.9), (previousClose * 1.1)}, 
						"買進價格必須是昨日收盤價的±10%之間");

				//告知現在該股票的作收與目前價格
				double currentPrice = yStock.getQuote().getPrice().doubleValue();
				errors.reject("price_info", String.format("昨收:%.2f 最新成交價:%.2f", previousClose,currentPrice));
			}
			
			if(amount < 1000) {
				errors.rejectValue("amount", "stock.amount.notenough");
			}
			
			if(amount % 1000 !=0) {
				errors.rejectValue("price", "stock.amount.range");
			}
		}catch (Exception e) {
			e.printStackTrace();
			if(yStock == null) {
				errors.rejectValue("symbol", "stock.symbol.notfound");
			}
		}
		
	}
	
}
