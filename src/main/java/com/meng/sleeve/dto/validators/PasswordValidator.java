package com.meng.sleeve.dto.validators;

import com.meng.sleeve.dto.PersonDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordEqual, PersonDTO> {
    @Override
    public boolean isValid(PersonDTO personDTO, ConstraintValidatorContext constraintValidatorContext) {
        return personDTO.getPassword1().equals(personDTO.getPassword2());
    }
}
