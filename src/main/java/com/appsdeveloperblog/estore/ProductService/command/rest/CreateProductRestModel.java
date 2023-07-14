package com.appsdeveloperblog.estore.ProductService.command.rest;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProductRestModel {
	
	//Data annotation of Lombok automatically created Get and Setter methods
	
	//@NotBlank (message = "The title is required")
	private String title;
	
	@Min (value=1, message = "The price cannot be lowered than 1")
	private BigDecimal price;
	
	@Min (value=1, message = "Quantity cannot be lowered than 1")
	@Max (value=5, message = "Quantity cannot be greater than 1")
	private Integer quantity;
	
	//private String prodId;

}
