package br.com.unipac.divan.divanapi.model.service;

import br.com.unipac.divan.divanapi.model.entities.user.Profile;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Profile repository.
 *
 * @author Rogério Fontes
 */
public interface UserProfileService extends BaseService<Profile, Long> {
    /**
     * Find by role list.
     *
     * @param profile the profile
     * @return the list
     */
    List<Profile> findByRole(@Param("role") String profile);
}
