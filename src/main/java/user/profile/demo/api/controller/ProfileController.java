package user.profile.demo.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import user.profile.demo.api.model.Profile;
import user.profile.demo.api.service.ProfileService;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);


    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Profile> getProfile(@PathVariable("userId") int userId) {
        Optional<Profile> optionalProfile = this.profileService.getByUserId(userId);
        return optionalProfile.map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<?> deleteProfile(@PathVariable("userId") int userId){
        profileService.delete(userId);
       return ResponseEntity.ok("user successfully deleted");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<String> createProfile(@RequestBody Profile profile){
        if (profileService.profileExist(profile.getUser().getId())) {
            this.profileService.create(profile);
            logger.info("profile added");
            return ResponseEntity.ok("profile successfully created");
        }
        logger.warn("profile already exist");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("current user already have profile");

    }

    @PutMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<String> updateProfile(@RequestBody Profile profile){
        profileService.update(profile);
        return  ResponseEntity.ok("profile successfully updated");
    }
}
