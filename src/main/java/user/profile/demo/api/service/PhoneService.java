package user.profile.demo.api.service;

import user.profile.demo.api.exception.DataBaseException;
import user.profile.demo.api.model.Phone;
import user.profile.demo.api.model.User;

import java.util.List;
import java.util.Optional;

public interface PhoneService {

    Phone create(Phone phone);

    Optional<Phone> get(int userId) throws DataBaseException;

    Optional<List<Phone>> getAll(int userId);

    void update(Phone phone);

    void delete(int id);


}
