package user.profile.demo.api.service;

import user.profile.demo.api.exception.DataBaseException;
import user.profile.demo.api.model.Profile;
import user.profile.demo.api.model.User;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Profile create(Profile profile);

    Optional<Profile> getByUserId(int userId);

    void update(Profile profile);

    void delete(int userId);

    boolean profileExist(int userId);
}
