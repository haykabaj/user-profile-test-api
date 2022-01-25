package user.profile.demo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.profile.demo.api.model.Phone;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository  extends JpaRepository<Phone, Integer> {

     Optional<Phone> findPhoneByUserId(int userId);
     Optional<List<Phone>> findAllByUserId(int userId);
}
