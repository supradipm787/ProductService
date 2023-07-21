package com.appsdeveloperblog.estore.ProductService.core.data;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupRepository extends JpaRepository <ProductLookupEntity, String> {
	ProductLookupEntity findByProductIdOrTitle (String productId, String title);
}
