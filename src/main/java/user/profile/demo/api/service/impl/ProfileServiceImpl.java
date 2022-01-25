package user.profile.demo.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import user.profile.demo.api.exception.DataBaseException;
import user.profile.demo.api.exception.ProfileException;
import user.profile.demo.api.model.Profile;
import user.profile.demo.api.repository.ProfileRepository;
import user.profile.demo.api.service.ProfileService;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = "bc")
public class ProfileServiceImpl implements ProfileService {

    private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Optional<Profile> getByUserId(int userId)  {
        return profileRepository.findProfileByUserId(userId);
    }

    @Override
    public void update(Profile profile) {
        Optional<Profile> profileFromDB =  profileRepository.findProfileByUserId(profile.getUser().getId());
        if (!profileFromDB.isPresent()){
            logger.error("profile does not exist");
            throw new ProfileException("profile related with user id does not exist");
        }
        profileFromDB.get().setCash(profile.getCash());
        profileRepository.save(profileFromDB.get());
        logger.info("profile successfully updated");
    }

    @Override
    public void delete(int userId) {
        profileRepository.deleteByUserId(userId);
    }


    @Override
    public boolean profileExist(int userId) {
        if (!profileRepository.findProfileByUserId(userId).isPresent()){
            return  true;
        }
        logger.warn("user already have profile");
        return false;
    }
}
