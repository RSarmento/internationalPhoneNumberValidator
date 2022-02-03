package pt.jumia.phonevalidator.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.jumia.phonevalidator.domain.Customer;
import pt.jumia.phonevalidator.repository.CustomerRepository;
import pt.jumia.phonevalidator.service.PhoneValidatorService;

@RestController
@RequestMapping("/validate")
public class InternationalPhoneNumberValidatorController {

    private final PhoneValidatorService phoneValidatorService;

    private final CustomerRepository customerRepository;

    public InternationalPhoneNumberValidatorController(PhoneValidatorService phoneValidatorService,
                                                       CustomerRepository customerRepository) {
        this.phoneValidatorService = phoneValidatorService;
        this.customerRepository = customerRepository;
    }

//    @GetMapping
//    public PhoneValidatorDto validate(@Valid @RequestParam(value = "phoneNumber") String phoneNumber){
//        return phoneValidatorService.validateInternationalPhoneNumber(phoneNumber);
//    }

    @GetMapping
    public ResponseEntity<Page<Customer>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.customerRepository.findAll(pageable));
    }
}
