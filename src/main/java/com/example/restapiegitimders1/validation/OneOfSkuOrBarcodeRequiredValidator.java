package com.example.restapiegitimders1.validation;

import com.example.restapiegitimders1.dto.ProductRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OneOfSkuOrBarcodeRequiredValidator implements ConstraintValidator<OneOfSkuOrBarcodeRequired,  ProductRequest> {

	@Override
	public boolean isValid(ProductRequest productRequest, ConstraintValidatorContext context) {
		if(productRequest == null) return true;
		
		return (productRequest.sku() != null && productRequest.barcode() == null) || 
				(productRequest.sku() == null && productRequest.barcode() != null);
	}

}
