package pt.jumia.phonevalidator.dto;

import lombok.Data;

@Data
public class PhoneValidatorDto {

    private String internationalPhoneNumber;
    private boolean isValid;
}
