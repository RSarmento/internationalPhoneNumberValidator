package pt.jumia.phonevalidator.service.impls;

import org.springframework.stereotype.Service;
import pt.jumia.phonevalidator.dto.PhoneValidatorDto;
import pt.jumia.phonevalidator.service.PhoneValidatorService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PhoneValidatorServiceImpl implements PhoneValidatorService {

    private static final String regexValidator = "^\\+[0-9]{1,3}\\.[0-9]{4,14}(?:x.+)?$";
    Pattern pattern;

    @Override
    public PhoneValidatorDto validateInternationalPhoneNumber(String phoneNumber){
        pattern = Pattern.compile(regexValidator);
        Matcher matcher = pattern.matcher(phoneNumber);

        PhoneValidatorDto phoneValidatorDto = new PhoneValidatorDto();
        phoneValidatorDto.setInternationalPhoneNumber(phoneNumber);
        phoneValidatorDto.setValid(matcher.matches());
        return phoneValidatorDto;
    }
}
