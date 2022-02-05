package pt.jumia.phonevalidator.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.jumia.phonevalidator.dto.PhoneDto;
import pt.jumia.phonevalidator.service.InternationalPhoneCategorizeService;

@RestController
@RequestMapping("/categorize")
public class InternationalPhoneCategorizeController {

    private final InternationalPhoneCategorizeService internationalPhoneCategorizeService;

    public InternationalPhoneCategorizeController(InternationalPhoneCategorizeService internationalPhoneCategorizeService) {
        this.internationalPhoneCategorizeService = internationalPhoneCategorizeService;
    }

    @GetMapping
    public ResponseEntity<Page<PhoneDto>> getAll(Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(internationalPhoneCategorizeService.getCategorizedNumbers(pageable));
    }

    @GetMapping("/byCountryAndState")
    public ResponseEntity<Page<PhoneDto>> getAllByCountryAndState(
            @RequestParam(value = "country", required = false, defaultValue = "") String country,
            @RequestParam(value = "state", required = false, defaultValue = "false") Boolean state,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(internationalPhoneCategorizeService.getCategorizedNumbersByCountryAndState(country, state, pageable));
    }
}
