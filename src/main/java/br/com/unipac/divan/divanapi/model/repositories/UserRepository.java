package br.com.unipac.divan.divanapi.model.repositories;

import br.com.unipac.divan.divanapi.model.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author Rogério Fontes
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findByEmail(String email);

    //Optional<User> findByUserId(Long id);
}

