package com.shoppingcart.shoppingservice.support.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

public class ProductAttributeResponse  {
	
	private Long id;

	
	private BigDecimal productAttributePrice;


	private Integer productOptionSortOrder;
	
	private boolean productAttributeIsFree;
	

	private BigDecimal productAttributeWeight;
	
	private boolean attributeDefault=false;
	
	private boolean attributeRequired=false;
	
	/**
	 * a read only attribute is considered as a core attribute addition
	 */
	private boolean attributeDisplayOnly=false;
	

	private boolean attributeDiscounted=false;
	

//	private ProductOption productOption;
	

//	private ProductOptionValue productOptionValue;
	
	
	/**
	 * This transient object property
	 * is a utility used only to submit from a free text
	 */
	private String attributePrice = "0";
	
	
	/**
	 * This transient object property
	 * is a utility used only to submit from a free text
	 */
	private String attributeSortOrder = "0";
	


	/**
	 * This transient object property
	 * is a utility used only to submit from a free text
	 */
	private String attributeAdditionalWeight = "0";
	
	public String getAttributePrice() {
		return attributePrice;
	}

	public void setAttributePrice(String attributePrice) {
		this.attributePrice = attributePrice;
	}

//	private Product product;
	
	public ProductAttributeResponse() {
	}


	public Integer getProductOptionSortOrder() {
		return productOptionSortOrder;
	}

	public void setProductOptionSortOrder(Integer productOptionSortOrder) {
		this.productOptionSortOrder = productOptionSortOrder;
	}

	public boolean getProductAttributeIsFree() {
		return productAttributeIsFree;
	}

	public void setProductAttributeIsFree(boolean productAttributeIsFree) {
		this.productAttributeIsFree = productAttributeIsFree;
	}

	public BigDecimal getProductAttributeWeight() {
		return productAttributeWeight;
	}

	public void setProductAttributeWeight(BigDecimal productAttributeWeight) {
		this.productAttributeWeight = productAttributeWeight;
	}

	public boolean getAttributeDefault() {
		return attributeDefault;
	}

	public void setAttributeDefault(boolean attributeDefault) {
		this.attributeDefault = attributeDefault;
	}

	public boolean getAttributeRequired() {
		return attributeRequired;
	}

	public void setAttributeRequired(boolean attributeRequired) {
		this.attributeRequired = attributeRequired;
	}

	public boolean getAttributeDisplayOnly() {
		return attributeDisplayOnly;
	}

	public void setAttributeDisplayOnly(boolean attributeDisplayOnly) {
		this.attributeDisplayOnly = attributeDisplayOnly;
	}

	public boolean getAttributeDiscounted() {
		return attributeDiscounted;
	}

	public void setAttributeDiscounted(boolean attributeDiscounted) {
		this.attributeDiscounted = attributeDiscounted;
	}

//	public ProductOption getProductOption() {
//		return productOption;
//	}
//
//	public void setProductOption(ProductOption productOption) {
//		this.productOption = productOption;
//	}
//
//	public ProductOptionValue getProductOptionValue() {
//		return productOptionValue;
//	}
//
//	public void setProductOptionValue(ProductOptionValue productOptionValue) {
//		this.productOptionValue = productOptionValue;
//	}
//
//	public Product getProduct() {
//		return product;
//	}

//	public void setProduct(Product product) {
//		this.product = product;
//	}
	
	
	public String getAttributeSortOrder() {
		return attributeSortOrder;
	}

	public void setAttributeSortOrder(String attributeSortOrder) {
		this.attributeSortOrder = attributeSortOrder;
	}

	public String getAttributeAdditionalWeight() {
		return attributeAdditionalWeight;
	}

	public void setAttributeAdditionalWeight(String attributeAdditionalWeight) {
		this.attributeAdditionalWeight = attributeAdditionalWeight;
	}
	
	public BigDecimal getProductAttributePrice() {
		return productAttributePrice;
	}

	public void setProductAttributePrice(BigDecimal productAttributePrice) {
		this.productAttributePrice = productAttributePrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}
