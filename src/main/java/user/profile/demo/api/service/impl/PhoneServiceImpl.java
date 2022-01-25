package user.profile.demo.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.profile.demo.api.exception.DataBaseException;
import user.profile.demo.api.exception.PhoneNotFoundException;
import user.profile.demo.api.model.Phone;
import user.profile.demo.api.model.User;
import user.profile.demo.api.repository.PhoneRepository;
import user.profile.demo.api.service.PhoneService;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    private static final Logger logger = LoggerFactory.getLogger(PhoneServiceImpl.class);
    private final PhoneRepository phoneRepository;
    private final UserServiceHelper userServiceHelper;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository, UserServiceHelper userServiceHelper) {
        this.phoneRepository = phoneRepository;
        this.userServiceHelper = userServiceHelper;
    }

    @Override
    public Phone create(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public Optional<Phone> get(int userId) throws DataBaseException {
        return phoneRepository.findById(userId);
    }

    @Override
    public Optional<List<Phone>> getAll(int userId) {
        return phoneRepository.findAllByUserId(userId);
    }

    @Override
    public void update(Phone phone) {
        Optional<Phone> phoneFromDB = phoneRepository.findPhoneByUserId(phone.getUser().getId());
        if (!phoneFromDB.isPresent()) {
            logger.error("error - dissimilarity of identifiers");
            throw new PhoneNotFoundException(String.valueOf(phone.getUser().getId()));
        }
        phoneFromDB.get().setValue(phone.getValue());
        phoneRepository.save(phoneFromDB.get());
    }

    @Override
    public void delete(int id) {
        User currentUser = userServiceHelper.getCurrentUser();
        Optional<Phone> phone = phoneRepository.findById(id);
        if (phone.isPresent()) {
            if (phone.get().getUser().getId() != currentUser.getId()) {
                logger.error("phone's user id does not match the current user id");
                throw new RuntimeException("phone's user id does not match the current user id");
            }
        }
        phoneRepository.deleteById(id);
        logger.info("phone successfully deleted");
    }
}
