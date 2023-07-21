package com.appsdeveloperblog.estore.ProductService.core.data;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table (name = "productlookup")
public class ProductLookupEntity implements Serializable {

	/**
	 * generated serial id to track deserialization
	 */
	private static final long serialVersionUID = -7872729502068498061L;
	@Id
	private String productId;
	
	@Column (unique = true)
	private String title;
	

}
