package user.profile.demo.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.profile.demo.api.exception.DataBaseException;
import user.profile.demo.api.exception.UserNotFoundException;
import user.profile.demo.api.exception.UserUpdateException;
import user.profile.demo.api.model.User;
import user.profile.demo.api.repository.UserRepository;
import user.profile.demo.api.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> get(int id) throws DataBaseException {
        return userRepository.findById(id);
    }

    @Override
    public void update(User user) {
        Optional<User> userFromDB = userRepository.findById(user.getId());
        if (!userFromDB.isPresent()) {
            logger.warn("warning - user user don't found");
            throw new UserNotFoundException("user like this can't found");
        }
        if (!checkEmail(user.getEmail())) {
            logger.warn("email can not change  because already taken ");
            throw new UserUpdateException("user can not update because email already taken");
        }
        userFromDB.get().setEmail(user.getEmail());
        userFromDB.get().setAge(user.getAge());
        userFromDB.get().setUsername(user.getUsername());
        userFromDB.get().setPassword(user.getPassword());
        userFromDB.get().setRole(user.getRole());
        userFromDB.get().setPhone(user.getPhone());
        logger.info("user successfully updated");
        userRepository.save(userFromDB.get());
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public Optional<User> userExist(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private boolean checkEmail(String email) {
        return !userRepository.findByEmail(email).isPresent();
    }
}
