package br.com.unipac.divan.divanapi.model.repositories;

import br.com.unipac.divan.divanapi.model.entities.user.Profile;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Profile repository.
 *
 * @author Rogério Fontes
 */
@Repository
@Qualifier(value = "userProfileRepository")
public interface UserProfileRepository extends JpaRepository<Profile, Long> {
    /**
     * Find by role list.
     *
     * @param profile the profile
     * @return the list
     */
    List<Profile> findByRole(@Param("role") String profile);
}
