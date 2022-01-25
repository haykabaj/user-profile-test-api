package user.profile.demo.api.controller;

import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import user.profile.demo.api.exception.DataBaseException;
import user.profile.demo.api.model.User;
import user.profile.demo.api.service.impl.UserServiceImpl;
import user.profile.demo.api.validation.ValidateEmail;

import java.util.Optional;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        try {
            Optional<User> userEntity = this.userService.get(id);
            return userEntity.map(ResponseEntity::ok).orElseGet(()
                    -> ResponseEntity.notFound().build());
        } catch (DataBaseException e) {
            logger.error("Error", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<String> createUser(@RequestBody @NotNull User user) {
        if(ValidateEmail.validateEmail(user.getEmail())) {
            if (!this.userService.userExist(user.getEmail()).isPresent()) {
                this.userService.create(user);
                logger.info("user added");
                return ResponseEntity.ok("user successfully added");
            }
        }
        logger.warn("user already exist or  invalid email format");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("user with this  email already exist or the entered data is incorrect");

    }


    @PutMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<String> updateUser(@RequestBody @NotNull User user) {
        userService.update(user);
        return ResponseEntity.ok("user successfully updated");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CHANGE')")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return ResponseEntity.ok("user deleted");
    }


}
