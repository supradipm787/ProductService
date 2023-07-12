package com.appsdeveloperblog.estore.ProductService.command;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.appsdeveloperblog.estore.ProductService.core.events.ProductCreatedEvent;

@Aggregate
public class ProductAggregate {
	
	@AggregateIdentifier
    private  String productId; 

	private  String title;
	
	private  BigDecimal price;
	
	private  Integer quantity;
	
	public ProductAggregate () {
		
	}
	
	
	@CommandHandler
	public ProductAggregate (CreateProductCommand createProductCommand) {
		
		if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException ("Price Cannot be empty") ;
			
		}
		if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()) {
			throw new IllegalArgumentException ("Title Cannot be empty") ;
		}
		
		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
		//copy the properties of source object  createProductCommand to destination object productCreatedEvent
		BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
		
		//apply method of AggregateLifecycle will publish the event to all the event handlers
		AggregateLifecycle.apply(productCreatedEvent) ;
	}
	
	/* This method will be first called once apply method of AggregateLifecycle is called  
	   This method publish the event to event bus after updating the state of Aggregate object */
	@EventSourcingHandler
	public void on (ProductCreatedEvent productCreatedEvent) {
		
		this.productId  =   productCreatedEvent.getProductId();
		this.title = productCreatedEvent.getTitle();
		this.quantity =   productCreatedEvent.getQuantity();
		this.price =  productCreatedEvent.getPrice();
		
	}

}
