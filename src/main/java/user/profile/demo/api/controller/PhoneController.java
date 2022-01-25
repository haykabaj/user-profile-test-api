package user.profile.demo.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import user.profile.demo.api.model.Phone;
import user.profile.demo.api.model.User;
import user.profile.demo.api.service.PhoneService;
import user.profile.demo.api.service.UserService;
import user.profile.demo.api.service.impl.UserServiceHelper;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/phones")
public class PhoneController {

    private final UserServiceHelper userServiceHelper;
    private final PhoneService phoneService;
    private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);


    @Autowired
    public PhoneController(UserServiceHelper userServiceHelper, PhoneService phoneService, UserService userService) {
        this.userServiceHelper = userServiceHelper;
        this.phoneService = phoneService;
    }


    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Phone>> getPhones(@PathVariable("userId") int userId) {
        Optional<List<Phone>> phones = this.phoneService.getAll(userId);
        return phones.map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<String> addPhone(@RequestBody Phone phone) {
        User user = userServiceHelper.getCurrentUser();
        if (phone.getUser().getId() != user.getId()) {
            logger.warn("phone id does not match the current user");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("phone id does not match the current user");
        }
        phoneService.create(phone);
        logger.info("phone successfully added");
        return ResponseEntity.ok("phone successfully added");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<String> updatePhone(@RequestBody Phone phone) {
        phoneService.update(phone);
        return ResponseEntity.ok("phone successfully updated");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<?> deletePhone(@PathVariable("id") int id) {
        phoneService.delete(id);
        return ResponseEntity.ok("user successfully deleted");
    }


}
