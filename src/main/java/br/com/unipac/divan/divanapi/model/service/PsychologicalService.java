package br.com.unipac.divan.divanapi.model.service;

import br.com.unipac.divan.divanapi.model.entities.psychological.Psychological;
import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalSpeciality;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 *
 * @author Rogério Fontes
 */
@Repository
public interface PsychologicalService extends BaseService<Psychological, Long> {
}
