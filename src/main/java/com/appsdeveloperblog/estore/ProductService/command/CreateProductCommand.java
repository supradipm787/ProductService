package com.appsdeveloperblog.estore.ProductService.command;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateProductCommand {
	
	//Builder annotation of Lombok to implement Builder design pattern
	//TargetAggregateIdentifier to target this command to some Aggregate object
	    @TargetAggregateIdentifier
	    private final String productId; 
	
		private final String title;
		
		private final BigDecimal price;
		
		private final Integer quantity;

}
