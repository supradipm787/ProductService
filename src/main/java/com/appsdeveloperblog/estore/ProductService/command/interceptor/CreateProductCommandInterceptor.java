package com.appsdeveloperblog.estore.ProductService.command.interceptor;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

//import org.apache.logging.log4j.Logger;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.estore.ProductService.command.CreateProductCommand;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor <CommandMessage <?>>{

	private static final Logger log = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);
	
	
	/*handle method returns a function 'BiFunction' which accepts Integer and CommandMessage 
	and return CommandMessage (last argument*/
	@Override
	public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
			List<? extends CommandMessage<?>> messages) {
		
		
		return (index, command) ->{
			
			log.info("PayLoadType : "  + command.getPayloadType());
			
			if (CreateProductCommand.class.equals(command.getPayloadType())) {
				
				CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
				
				if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
					throw new IllegalArgumentException ("Price Cannot be empty") ;
					
				}
				if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()) {
					throw new IllegalArgumentException ("Title Cannot be empty") ;
				}
			}
			
			return command;
		};
		
	}

	
	

}
