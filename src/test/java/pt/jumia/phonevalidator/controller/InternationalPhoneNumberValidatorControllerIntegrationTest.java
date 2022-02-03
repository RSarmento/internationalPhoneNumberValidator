package pt.jumia.phonevalidator.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class InternationalPhoneNumberValidatorControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private static final String CONTROLLER_ROUTE = "/validate";
    private static final String CONTROLLER_PARAM = "phoneNumber";
    private static final String VALID_INTERNATIONAL_PHONE_NUMBER = "+123.123456x4444";
    private static final String INVALID_INTERNATIONAL_PHONE_NUMBER = "asjkbc+123.123456x44-----440000000000";

    @Nested
    @DisplayName("When valid number")
    class WhenValidNumber{

        @Test
        @DisplayName("Is properly sent")
        void isProperlySentShouldReturnTrue() throws Exception {
            mvc.perform(get(CONTROLLER_ROUTE)
                            .param(CONTROLLER_PARAM, VALID_INTERNATIONAL_PHONE_NUMBER))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.valid", is(true)));
        }

        @Test
        @DisplayName("Is Not Properly Sent")
        void isNotProperlySentShouldReturnError() throws Exception {
            mvc.perform(get(CONTROLLER_ROUTE)
                            .param(CONTROLLER_PARAM + "incorrectParamName", VALID_INTERNATIONAL_PHONE_NUMBER))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("When invalid number")
    class WhenInvalidNumber{

        @Test
        @DisplayName("Is properly sent")
        void isProperlySentShouldReturnFalse() throws Exception {
            mvc.perform(get(CONTROLLER_ROUTE)
                            .param(CONTROLLER_PARAM, INVALID_INTERNATIONAL_PHONE_NUMBER))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.valid", is(false)));
        }

    }
}