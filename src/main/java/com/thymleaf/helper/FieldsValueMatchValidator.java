package com.thymleaf.helper;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object>
{
    private String field;
    private String fieldMatch;
    private String message;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation)
    {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
        this.message = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context)
    {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);

        boolean valid = true;

        if (fieldValue != null)
        {
            valid =  fieldValue.equals(fieldMatchValue);
        }
        else
        {
            valid = fieldMatchValue == null;
        }

        if (!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(field)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
