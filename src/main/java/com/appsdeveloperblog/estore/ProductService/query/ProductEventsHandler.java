package com.appsdeveloperblog.estore.ProductService.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.estore.ProductService.core.data.ProductEntity;
import com.appsdeveloperblog.estore.ProductService.core.data.ProductRepository;
import com.appsdeveloperblog.estore.ProductService.core.events.ProductCreatedEvent;

@ProcessingGroup ("product-group")
@Component
public class ProductEventsHandler {
	private final ProductRepository productRepository; 
	
	public ProductEventsHandler (ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	
	@ExceptionHandler (resultType = IllegalArgumentException.class)
	public void handle (IllegalArgumentException e) {
		//log error message
	}
	
	@ExceptionHandler (resultType = Exception.class) 
	public void handle (Exception e) throws Exception {
		//log error message
		throw e;
	}
	
	
	@EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) throws Exception {
		ProductEntity productEntity = new ProductEntity();
		
		BeanUtils.copyProperties(productCreatedEvent, productEntity);
		
		try {
			//saving ProductEntity to DB
			productRepository.save(productEntity); 			
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		/** if (true) {
			throw new Exception ("Forcing Exception in the Event Handler class");
		}**/
		
    }
}
