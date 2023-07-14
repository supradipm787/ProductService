package com.appsdeveloperblog.estore.ProductService.query.rest;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductQueryRestModel {
	
	private String productId ;
	
	private  String title;
	
	private  BigDecimal price;
	private  Integer quantity  ;

}
