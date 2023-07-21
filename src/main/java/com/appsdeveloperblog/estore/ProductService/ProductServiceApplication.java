package com.appsdeveloperblog.estore.ProductService;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import com.appsdeveloperblog.estore.ProductService.command.interceptor.CreateProductCommandInterceptor;
import com.appsdeveloperblog.estore.ProductService.core.errorhandling.ProductServiceEventsErrorHandler;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
	
	/* register CreateProductCommandInterceptor class which intercepts messages before passing to messagebus */
	@Autowired
	public void registerCreateProductCommandInterceptor (ApplicationContext context, CommandBus commandBus) {
		commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
		
	}
	
	@Autowired
	public void configure (EventProcessingConfigurer eventProcessingConfigurer) {
		//register Exception handling listener for Event Handler
		eventProcessingConfigurer.registerListenerInvocationErrorHandler("product-group", conf -> new ProductServiceEventsErrorHandler ());
		
	}

}
