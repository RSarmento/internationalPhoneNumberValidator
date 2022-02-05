package pt.jumia.phonevalidator.dto;

import lombok.Data;
import pt.jumia.phonevalidator.domain.Customer;

@Data
public class PhoneDto {
    private String countryCode;
    private String countryName;
    private Boolean state;
    private String number;

    public PhoneDto(Customer customer) {
        this.number = customer.getPhone();
    }
}
