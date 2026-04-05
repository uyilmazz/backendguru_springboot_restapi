package com.example.restapiegitimders1.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OneOfSkuOrBarcodeRequiredValidator.class)
public @interface OneOfSkuOrBarcodeRequired {
	
	String message() default "Either SKU or Barcode must be provided, but not both";
	Class<?>[] groups() default {};
	Class<? extends jakarta.validation.Payload>[] payload() default {};
}
