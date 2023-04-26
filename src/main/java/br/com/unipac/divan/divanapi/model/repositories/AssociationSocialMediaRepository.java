package br.com.unipac.divan.divanapi.model.repositories;

import br.com.unipac.divan.divanapi.model.entities.association.AssociationSocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationSocialMediaRepository  extends JpaRepository<AssociationSocialMedia, Long>  {
}
