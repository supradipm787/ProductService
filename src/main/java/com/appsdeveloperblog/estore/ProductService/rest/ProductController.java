package com.appsdeveloperblog.estore.ProductService.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.estore.ProductService.command.CreateProductCommand;
import com.appsdeveloperblog.estore.ProductService.model.ProductRestModel;
import com.appsdeveloperblog.estore.ProductService.utils.EnvironmentConstants;
import com.appsdeveloperblog.estore.ProductService.utils.MappingConstants;
import com.appsdeveloperblog.estore.ProductService.utils.MethodConstants;

@RestController
@RequestMapping (MappingConstants.productMapping)
public class ProductController {
	
	
	private final Environment env; // To handle/set runtime environment
	private final CommandGateway commandgateway; //object of AxomFramework to set command to Command Bus
	
	@Autowired
	public ProductController (Environment env, CommandGateway commandgateway) {
		this.env = env;
		this.commandgateway = commandgateway;
	}
	
	@DeleteMapping
	public String deleteProduct() {
		return MethodConstants.deleteHandled;
		
	}
	@PostMapping
	public String createProduct(@RequestBody ProductRestModel productRestModel) {
		
		//Create Instance of CreateProductCommand class using Builder 
		CreateProductCommand createProductCommand = CreateProductCommand.builder()
				.price(productRestModel.getPrice())
				.quantity(productRestModel.getQuantity())				
				.title(productRestModel.getTitle())
				.productId(UUID.randomUUID().toString()).build();
				
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
	@GetMapping
	public String getProduct() {
		return MethodConstants.getHandled + env.getProperty(EnvironmentConstants.localServerPort);
		
	}
	@PutMapping
	public String putProduct() {
		return MethodConstants.putHandled;
		
	}
	

}
