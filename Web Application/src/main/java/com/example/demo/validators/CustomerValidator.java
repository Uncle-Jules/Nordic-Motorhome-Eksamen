package com.example.demo.validators;

import com.example.demo.models.Customer;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CustomerValidator implements Validator {
    @Override
    //
    public boolean supports(Class aClass) {
        return Customer.class.isAssignableFrom(aClass);
    }


    @Override
    public void validate(Object target, Errors errors) {
       // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone_number", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birth_date", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "drivers_license", "field.required");
        Customer customer = (Customer) target;
    }
}
