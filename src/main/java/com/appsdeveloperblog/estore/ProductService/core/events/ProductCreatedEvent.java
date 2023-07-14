package com.appsdeveloperblog.estore.ProductService.core.events;

import java.math.BigDecimal;

import lombok.Data;


@Data
public class ProductCreatedEvent {	
	public ProductCreatedEvent () {
		
	}

		private String productId ;
		
		private  String title;
		
		private  BigDecimal price;
		private  Integer quantity  ;
		
		

}
