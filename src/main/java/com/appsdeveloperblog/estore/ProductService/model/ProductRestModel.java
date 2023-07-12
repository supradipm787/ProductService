package com.appsdeveloperblog.estore.ProductService.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

//import io.micrometer.common.lang.NonNull;
//import io.micrometer.common.lang.Nullable;
import lombok.Data;
//import lombok.Getter;


@Data
public class ProductRestModel {
	//Data annotation of Lombok automatically created Get and Setter methods
	@NotNull (message = "The title is required")
	private String title;
	@NotNull (message = "The price is required")
	private BigDecimal price;
	@NotNull (message = "The quantity is required")
	private Integer quantity;
	
	//private String prodId;

	
}
