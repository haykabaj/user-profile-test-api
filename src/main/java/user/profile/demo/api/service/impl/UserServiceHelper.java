package user.profile.demo.api.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import user.profile.demo.api.exception.UserNotFoundException;
import user.profile.demo.api.model.User;
import user.profile.demo.api.service.UserService;

import java.util.Optional;

@Service
public class UserServiceHelper {
    private final UserService userService;

    public UserServiceHelper(UserService userService) {
        this.userService = userService;
    }

    public User getCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        Optional<User> optionalUser = userService.getUserByEmail(email);
        return optionalUser.orElseThrow(() -> new UserNotFoundException("can not find user"));
    }
}
