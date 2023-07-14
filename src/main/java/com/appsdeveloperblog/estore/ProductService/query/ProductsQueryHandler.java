package com.appsdeveloperblog.estore.ProductService.query;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.estore.ProductService.core.data.ProductEntity;
import com.appsdeveloperblog.estore.ProductService.core.data.ProductRepository;
import com.appsdeveloperblog.estore.ProductService.query.rest.ProductQueryRestModel;

@Component
public class ProductsQueryHandler {
	
	private final ProductRepository productRepository;
	public ProductsQueryHandler (ProductRepository productRepository) {
		this.productRepository = productRepository;
		
	}
	
	@QueryHandler
	public List<ProductQueryRestModel> getProducts(FindProductsQuery findProductsQuery ){
		
		List <ProductQueryRestModel> productQueryRestModelList = new ArrayList <ProductQueryRestModel> ();		
		List <ProductEntity> productEntityList = productRepository.findAll();
		
		for (ProductEntity productEntity : productEntityList) {
			
			ProductQueryRestModel productQueryRestModel  = new ProductQueryRestModel();
			BeanUtils.copyProperties(productEntity, productQueryRestModel);
			
			productQueryRestModelList.add(productQueryRestModel);
		}		
		
		return productQueryRestModelList;
	}

}
