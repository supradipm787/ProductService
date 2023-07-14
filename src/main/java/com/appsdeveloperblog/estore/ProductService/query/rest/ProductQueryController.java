package com.appsdeveloperblog.estore.ProductService.query.rest;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.estore.ProductService.query.FindProductsQuery;

//import com.appsdeveloperblog.estore.ProductService.model.ProductRestModel;

import com.appsdeveloperblog.estore.ProductService.utils.MappingConstants;

@RestController
@RequestMapping (MappingConstants.productMapping)
public class ProductQueryController {
	
	@Autowired
	QueryGateway queryGateway;
	
	@GetMapping
	public List<ProductQueryRestModel> getProducts(){
		
		FindProductsQuery findProductsQuery = new FindProductsQuery();
		List<ProductQueryRestModel> products =  queryGateway.query(findProductsQuery, 
				ResponseTypes.multipleInstancesOf(ProductQueryRestModel.class)).join();
				
		
		return products;
		
	}
	

}
