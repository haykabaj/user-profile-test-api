package user.profile.demo.api.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotFoundException extends AuthenticationException {

        public UserNotFoundException(String username) {
            super("The user with username" + username+ "was not found");

        }



}
