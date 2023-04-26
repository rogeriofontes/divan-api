package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.user.Profile;
import br.com.unipac.divan.divanapi.model.repositories.UserProfileRepository;
import br.com.unipac.divan.divanapi.model.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The interface Profile repository.
 *
 * @author Rogério Fontes
 */
@Service
@Qualifier(value = "userProfileRepository")
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    /**
     * Find by role list.
     *
     * @param profile the profile
     * @return the list
     */
    @Override
    public List<Profile> findByRole(@Param("role") String profile){
        return userProfileRepository.findByRole(profile);
    }

    @Override
    public Profile save(Profile profile) throws Exception {
        return userProfileRepository.save(profile);
    }

    @Override
    public Profile edit(Long id, Profile profile) {
        Optional<Profile> resultById = findById(id);

        if (resultById.isPresent()) {
            Profile profileToChange = resultById.get();
            profileToChange.update(id, profile);
            return userProfileRepository.save(profile);
        }

        return null;
    }

    @Override
    public List<Profile> listAll() {
        return userProfileRepository.findAll();
    }

    @Override
    public Page<Profile> findAllPageable(Pageable pageable) {
        return userProfileRepository.findAll(pageable);
    }

    @Override
    public Optional<Profile> findById(Long id) {
        return userProfileRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            userProfileRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
