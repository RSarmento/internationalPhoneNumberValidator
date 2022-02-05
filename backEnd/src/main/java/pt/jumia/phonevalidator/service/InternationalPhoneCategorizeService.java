package pt.jumia.phonevalidator.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.jumia.phonevalidator.dto.PhoneDto;

public interface InternationalPhoneCategorizeService {

    Page<PhoneDto> getCategorizedNumbers(Pageable pageable);

    Page<PhoneDto> getCategorizedNumbersByCountryAndState(String country, Boolean state, Pageable pageable);
}
