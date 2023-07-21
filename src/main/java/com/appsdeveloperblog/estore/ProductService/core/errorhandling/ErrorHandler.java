package com.appsdeveloperblog.estore.ProductService.core.errorhandling;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class ErrorHandler {
	private final String message;
	private final Date timestamp;

}
