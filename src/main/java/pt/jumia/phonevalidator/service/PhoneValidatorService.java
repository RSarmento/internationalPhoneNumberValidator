package pt.jumia.phonevalidator.service;

import pt.jumia.phonevalidator.dto.PhoneValidatorDto;

public interface PhoneValidatorService {

    PhoneValidatorDto validateInternationalPhoneNumber(String phoneValidatorDto);
}
