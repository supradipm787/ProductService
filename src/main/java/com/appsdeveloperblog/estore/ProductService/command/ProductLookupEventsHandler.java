package com.appsdeveloperblog.estore.ProductService.command;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.estore.ProductService.core.data.ProductLookupEntity;
import com.appsdeveloperblog.estore.ProductService.core.data.ProductLookupRepository;
import com.appsdeveloperblog.estore.ProductService.core.events.ProductCreatedEvent;

@Component
/** Processing group helps to group the event handlers & process same kind of events (here product lookup & handling events are grouped)
 * This is core to Axon framework and used to track the same kind of events in the same processing thread by grouping handlers  
 */
@ProcessingGroup ("product-group")
public class ProductLookupEventsHandler {
	private final ProductLookupRepository  productLookupRepository;
	
	public ProductLookupEventsHandler (ProductLookupRepository productLookupRepository) {
		this.productLookupRepository = productLookupRepository;
		
	}
	
	@EventHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		
		ProductLookupEntity productLookupEntity = new ProductLookupEntity(productCreatedEvent.getProductId(), productCreatedEvent.getTitle());
		productLookupRepository.save(productLookupEntity);
	}

}
