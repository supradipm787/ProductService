package com.appsdeveloperblog.estore.ProductService.command.rest;


import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.estore.ProductService.command.CreateProductCommand;
import com.appsdeveloperblog.estore.ProductService.command.interceptor.CreateProductCommandInterceptor;
//import com.appsdeveloperblog.estore.ProductService.model.ProductRestModel;
//import com.appsdeveloperblog.estore.ProductService.utils.EnvironmentConstants;
import com.appsdeveloperblog.estore.ProductService.utils.MappingConstants;

import jakarta.validation.Valid;
//import com.appsdeveloperblog.estore.ProductService.utils.MethodConstants;

@RestController
@RequestMapping (MappingConstants.productMapping)
public class ProductCommandController {
	
	
	private final Environment env; // To handle/set runtime environment
	private final CommandGateway commandgateway; //object of AxomFramework to set command to Command Bus
	private static final Logger log = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);
	
	@Autowired
	public ProductCommandController (Environment env, CommandGateway commandgateway) {
		this.env = env;
		this.commandgateway = commandgateway;
	}
	
	/** @DeleteMapping
	public String deleteProduct() {
		return MethodConstants.deleteHandled;
		
	}**/
	
	//create ProductRestModel from RequestBody and pass it to createProduct method
	@PostMapping
	public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
		
		//Create Instance of CreateProductCommand class using Builder 
		CreateProductCommand createProductCommand = CreateProductCommand.builder()
				.price(createProductRestModel.getPrice())
				.quantity(createProductRestModel.getQuantity())				
				.title(createProductRestModel.getTitle())
				.productId(UUID.randomUUID().toString()).build();
		
		log.info("Title in RequestBody in ProductCommandController  : "  + createProductRestModel.getTitle() );
				
		String returnValueFromCommand ;
		
		try {
			returnValueFromCommand = commandgateway.sendAndWait(createProductCommand);
		}catch (Exception e) {
			returnValueFromCommand = e.getLocalizedMessage();
		}
		
		//RequestBody annotation converts the request body content of ProductRestModel to object of ProductRestModel and inject to createProduct method
		//return MethodConstants.postHandled + productRestModel.getTitle();		
		
		return returnValueFromCommand;
	}
	
	/** @GetMapping
	public String getProduct() {
		return MethodConstants.getHandled + env.getProperty(EnvironmentConstants.localServerPort);
		
	}
	@PutMapping
	public String putProduct() {
		return MethodConstants.putHandled;
		
	}**/
	

}
