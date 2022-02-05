package pt.jumia.phonevalidator.validatorEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public enum CountryNumberValidatorEnum {
    CAMEROON {
        public String getCountryCode() { return "237"; }

        public boolean isCountry(String countryCode) { return countryCode.equals(getCountryCode()); }

        public boolean isStateValid(String number) {
            Pattern pattern = Pattern.compile("\\(237\\)\\ ?[2368]\\d{7,8}$");
            Matcher matcher = pattern.matcher(number);
            return matcher.matches();
        }
    },
    ETHIOPIA {
        public String getCountryCode() { return "251"; }

        public boolean isCountry(String countryCode) { return countryCode.equals(getCountryCode()); }

        public boolean isStateValid(String number) {
            Pattern pattern = Pattern.compile("\\(251\\)\\ ?[1-59]\\d{8}$");
            Matcher matcher = pattern.matcher(number);
            return matcher.matches();
        }
    },
    MOROCCO {
        public String getCountryCode() { return "212"; }

        public boolean isCountry(String countryCode) { return countryCode.equals(getCountryCode()); }

        public boolean isStateValid(String number) {
            Pattern pattern = Pattern.compile("\\(212\\)\\ ?[5-9]\\d{8}$");
            Matcher matcher = pattern.matcher(number);
            return matcher.matches();
        }
    },
    MOZAMBIQUE {
        public String getCountryCode() { return "258"; }

        public boolean isCountry(String countryCode) { return countryCode.equals(getCountryCode()); }

        public boolean isStateValid(String number) {
            Pattern pattern = Pattern.compile("\\(258\\)\\ ?[28]\\d{7,8}$");
            Matcher matcher = pattern.matcher(number);
            return matcher.matches();
        }
    },
    UGANDA {
        public String getCountryCode() { return "256"; }

        public boolean isCountry(String countryCode) { return countryCode.equals(getCountryCode()); }

        public boolean isStateValid(String number) {
            Pattern pattern = Pattern.compile("\\(256\\)\\ ?\\d{9}$");
            Matcher matcher = pattern.matcher(number);
            return matcher.matches();
        }
    };

    public abstract boolean isStateValid(String number);

    public abstract boolean isCountry(String countryCode);

    public abstract String getCountryCode();

}
