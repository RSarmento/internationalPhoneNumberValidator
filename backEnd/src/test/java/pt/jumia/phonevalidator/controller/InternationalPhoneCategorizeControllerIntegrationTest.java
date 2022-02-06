package pt.jumia.phonevalidator.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pt.jumia.phonevalidator.validatorEnum.CountryNumberValidatorEnum;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class InternationalPhoneCategorizeControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private static final String CONTROLLER_ROUTE_CATEGORIZE     = "/categorize";
    private static final String CONTROLLER_ROUTE_COUNTRY_STATE  = CONTROLLER_ROUTE_CATEGORIZE + "/byCountryAndState";
    private static final String CONTROLLER_PARAM_COUNTRY        = "country";
    private static final String CONTROLLER_PARAM_STATE          = "state";

    @Nested
    @DisplayName("When query all")
    class WhenQueryAll {
        @Test
        @DisplayName("Is properly sent")
        void shouldReturnAllNumbers() throws Exception {
            mvc.perform(get(CONTROLLER_ROUTE_CATEGORIZE))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content", hasSize(20)))
                    .andExpect(jsonPath("$.totalPages", is(3)))
                    .andExpect(jsonPath("$.totalElements", is(41)))
                    .andExpect(jsonPath("$.pageable.pageNumber", is(0)));
        }
    }

    @Nested
    @DisplayName("When query by country")
    class WhenQueryByCountry {
        @Test
        @DisplayName("Should return expected countries")
        void shouldReturnExpectedCountries() throws Exception {
            mvc.perform(get(CONTROLLER_ROUTE_COUNTRY_STATE)
                            .param(CONTROLLER_PARAM_COUNTRY, CountryNumberValidatorEnum.CAMEROON.name()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content", hasSize(3)))
                    .andExpect(jsonPath("$.content[0].countryName", is(CountryNumberValidatorEnum.CAMEROON.name())))
                    .andExpect(jsonPath("$.totalPages", is(1)))
                    .andExpect(jsonPath("$.totalElements", is(3)))
                    .andExpect(jsonPath("$.pageable.pageNumber", is(0)));
        }

        @Test
        @DisplayName("Should return empty list when country not found")
        void shouldReturnEmptyListWhenCountryNotFound() throws Exception {
            mvc.perform(get(CONTROLLER_ROUTE_COUNTRY_STATE)
                            .param(CONTROLLER_PARAM_COUNTRY, "Brazil"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content", hasSize(0)));
        }
    }

    @Nested
    @DisplayName("When query by state")
    class WhenQueryByState {
        @Test
        @DisplayName("Should return expected state")
        void shouldReturnExpectedState() throws Exception {
            mvc.perform(get(CONTROLLER_ROUTE_COUNTRY_STATE)
                            .param(CONTROLLER_PARAM_STATE, "true"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content", hasSize(20)))
                    .andExpect(jsonPath("$.content[0].state", is(true)))
                    .andExpect(jsonPath("$.totalPages", is(2)))
                    .andExpect(jsonPath("$.totalElements", is(27)))
                    .andExpect(jsonPath("$.pageable.pageNumber", is(0)));
        }
    }

    @Nested
    @DisplayName("When query by country")
    class WhenQueryByCountryAndState {

        @Test
        @DisplayName("Should return expected country and state")
        void shouldReturnExpectedCountryAndState() throws Exception {
            mvc.perform(get(CONTROLLER_ROUTE_COUNTRY_STATE)
                            .param(CONTROLLER_PARAM_COUNTRY, CountryNumberValidatorEnum.ETHIOPIA.name())
                            .param(CONTROLLER_PARAM_STATE, "false"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[0].state", is(false)))
                    .andExpect(jsonPath("$.content[0].countryName", is(CountryNumberValidatorEnum.ETHIOPIA.name())))
            ;
        }

        @Test
        @DisplayName("Should return empty list when country not found")
        void shouldReturnEmptyListWhenCountryNotFound() throws Exception {
            mvc.perform(get(CONTROLLER_ROUTE_CATEGORIZE)
                            .param(CONTROLLER_ROUTE_COUNTRY_STATE, "Japan")
                            .param(CONTROLLER_PARAM_STATE, "false"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content", hasSize(20)));
        }
    }
}
