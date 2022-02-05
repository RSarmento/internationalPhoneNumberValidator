package pt.jumia.phonevalidator.service.impls;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pt.jumia.phonevalidator.dto.PhoneDto;
import pt.jumia.phonevalidator.repository.CustomerRepository;
import pt.jumia.phonevalidator.service.InternationalPhoneCategorizeService;
import pt.jumia.phonevalidator.validatorEnum.CountryNumberValidatorEnum;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternationalPhoneCategorizeServiceImpl implements InternationalPhoneCategorizeService {

    private final CustomerRepository customerRepository;

    public InternationalPhoneCategorizeServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<PhoneDto> getCategorizedNumbers(Pageable pageable) {
        return this.customerRepository
                .findAll(pageable)
                .map(PhoneDto::new)
                .map(this::categorizeNumber);
    }

    @Override
    public Page<PhoneDto> getCategorizedNumbersByCountryAndState(String country, Boolean state, Pageable pageable) {
        List<PhoneDto> phoneDtoList = this.customerRepository
                .findAll()
                .stream()
                .map(PhoneDto::new)
                .map(this::categorizeNumber)
                .collect(Collectors.toList());

        if (!country.isEmpty())
            phoneDtoList = filterSearchResultByCountry(phoneDtoList, country);

        if (state != null)
            phoneDtoList = filterSearchResultByState(phoneDtoList, state);

        return paginateSearchResult(phoneDtoList, pageable);
    }

    private List<PhoneDto> filterSearchResultByCountry(List<PhoneDto> phoneDtoList, String country) {
        return phoneDtoList.stream()
                .filter(phoneDto -> country.equalsIgnoreCase(phoneDto.getCountryName()))
                .collect(Collectors.toList());
    }

    private List<PhoneDto> filterSearchResultByState(List<PhoneDto> phoneDtoList, boolean state) {
        return phoneDtoList.stream()
                .filter(phoneDto -> state == phoneDto.getState())
                .collect(Collectors.toList());
    }

    private Page<PhoneDto> paginateSearchResult(List<PhoneDto> phoneDtoList, Pageable pageable) {
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), phoneDtoList.size());
        return new PageImpl<>(phoneDtoList.subList(start, end), pageable, phoneDtoList.size());
    }

    private PhoneDto categorizeNumber(PhoneDto phoneDto) {
        for (CountryNumberValidatorEnum value : CountryNumberValidatorEnum.values()) {
            if (value.isCountry(phoneDto.getNumber().substring(1, 4))){
                phoneDto.setState(value.isStateValid(phoneDto.getNumber()));
                phoneDto.setCountryName(value.name());
                phoneDto.setCountryCode(value.getCountryCode());
                phoneDto.setNumber(phoneDto.getNumber().substring(5));
                return phoneDto;
            }
        }
        return phoneDto;
    }
}
