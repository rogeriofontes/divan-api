package br.com.unipac.divan.divanapi.model.repositories;

import br.com.unipac.divan.divanapi.model.entities.association.Association;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Long> {
}
