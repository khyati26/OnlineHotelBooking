package com.testspring.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

@Component
public class HotelValidator implements Validator {

    @Autowired
    private Validator addressValidator;

    public HotelValidator() {
    }

    public HotelValidator(Validator addressValidator) {
        this.addressValidator = addressValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Hotel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Hotel hotel = (Hotel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "hotel.name");
        if (hotel.getId() == 0 || hotel.getId() < 0) {
            errors.rejectValue("id", "hotel.id");
        }
        errors.pushNestedPath("address");
        ValidationUtils.invokeValidator(addressValidator, hotel.getAddress(), errors);
    }
}
