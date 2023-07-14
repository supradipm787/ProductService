package com.appsdeveloperblog.estore.ProductService.query;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.estore.ProductService.core.data.ProductEntity;
import com.appsdeveloperblog.estore.ProductService.core.data.ProductRepository;
import com.appsdeveloperblog.estore.ProductService.core.events.ProductCreatedEvent;

@Component
public class ProductEventsHandler {
	private final ProductRepository productRepository; 
	
	public ProductEventsHandler (ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
		ProductEntity productEntity = new ProductEntity();
		
		BeanUtils.copyProperties(productCreatedEvent, productEntity);
		
		//saving ProductEntity to DB
		productRepository.save(productEntity); 
    }
}
