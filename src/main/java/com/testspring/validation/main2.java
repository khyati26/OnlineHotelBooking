package com.testspring.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class main2 {
    public static void main(String[] args) {

        //MapBindingResult
        //BeanPropertyBindingResult




        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Hotel hotel = new Hotel();
        hotel.setId(-1);
//        hotel.setName("ABCDEF");
        hotel.setEmail("fdgdsgdg");
        hotel.setMobile(null);

        Address address1 = new Address();
        address1.setArea(null);
        address1.setCountry(null);
        address1.setState(null);

        hotel.setAddress(address1);

        AddressValidator addressValidator = new AddressValidator();
        BindingResult bindingResult = new BeanPropertyBindingResult(hotel,"hotel");
        org.springframework.validation.Validator springValidator = new HotelValidator(addressValidator);
        springValidator.validate(hotel,bindingResult);
        if(bindingResult.hasErrors()){
            System.out.println("Error exist...");
            System.out.println(bindingResult.getFieldError().getField());
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//            List<FieldError> fieldErrorList = bindingResult.getFieldErrors("mobile");
            for(FieldError fieldError : fieldErrorList){
                System.out.println(fieldError.getField() + "=" +fieldError.getCode());
                System.out.println(fieldError.getDefaultMessage());
                System.out.println("=========================");
            }

            System.out.println(bindingResult.getErrorCount());
            System.out.println(bindingResult.getFieldErrorCount());
            System.out.println(bindingResult.getGlobalErrorCount());

            List<ObjectError> globalErr = bindingResult.getGlobalErrors();
            for(ObjectError objectError : globalErr){
                System.out.println(objectError.getCode() + "=" + objectError.getDefaultMessage());
            }
        }

//        Set<ConstraintViolation<Hotel>> violations = validator.validate(hotel);
//        for (ConstraintViolation<Hotel> errorMessages : violations) {
//            System.out.println(errorMessages.getMessage());
//        }

//        System.out.println("========================================================\n");
//
//        Customer customer = new Customer();
//        customer.setAge(null);
//        customer.setEmail(null);
//        customer.setName(null);
//
////        customer.setBirthday("12-0-20");
////        customer.setGender(null);
//
//        Set<ConstraintViolation<Customer>> violations1 = validator.validate(customer);
//        for (ConstraintViolation<Customer> errorMessages : violations1) {
//            System.out.println(errorMessages.getMessage());
//        }

    }
}
