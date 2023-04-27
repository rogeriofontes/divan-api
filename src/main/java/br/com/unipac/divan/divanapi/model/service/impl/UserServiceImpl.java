package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.user.Profile;
import br.com.unipac.divan.divanapi.model.entities.user.User;
import br.com.unipac.divan.divanapi.model.repositories.UserRepository;
import br.com.unipac.divan.divanapi.model.service.BaseService;
import br.com.unipac.divan.divanapi.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author Rogério Fontes
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    @Override
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public User edit(Long id, User user) {
        Optional<User> resultById = findById(id);

        if (resultById.isPresent()) {
            User userToChange = resultById.get();
            userToChange.update(id, user);
            return userRepository.save(user);
        }

        return null;
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAllPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            userRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
    //Optional<User> findByUserId(Long id);
}

