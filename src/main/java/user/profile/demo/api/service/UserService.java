package user.profile.demo.api.service;

import user.profile.demo.api.exception.DataBaseException;
import user.profile.demo.api.model.User;

import java.util.Optional;

public interface UserService {

    User create(User user);

    Optional<User> get(int id) throws DataBaseException;

    void update(User user);

    void delete(Integer id);

    Optional<User> userExist(String email);

    Optional<User> getUserByEmail(String email);
}
