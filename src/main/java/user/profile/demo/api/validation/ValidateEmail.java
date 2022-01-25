package user.profile.demo.api.validation;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidateEmail {
    public static boolean  validateEmail(String email){
        email = email.trim();
        EmailValidator eValidator = EmailValidator.getInstance();
        return eValidator.isValid(email);
    }


}
