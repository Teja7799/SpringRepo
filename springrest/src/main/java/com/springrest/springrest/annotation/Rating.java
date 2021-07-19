package com.springrest.springrest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=RatingValidation.class)

public @interface Rating {
	
	String message() default "Rating Standards are upto 5 ";
	Class<?>[]groups()default {};
	public abstract Class<? extends Payload>[] payload() default{};

}
