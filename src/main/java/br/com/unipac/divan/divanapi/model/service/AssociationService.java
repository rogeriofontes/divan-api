package br.com.unipac.divan.divanapi.model.service;

import br.com.unipac.divan.divanapi.model.entities.association.Association;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationService extends BaseService<Association, Long> {
}
