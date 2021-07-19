package com.springrest.springrest.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RatingValidation implements ConstraintValidator<Rating,String>{

	@Override
	public boolean isValid(String Rating, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (Rating == null || Rating.matches("[0-5]+") && Rating.length() == 1) {
			return true;
		} else {
			return false;
		}
		
	}

}
