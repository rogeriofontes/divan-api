package br.com.unipac.divan.divanapi.model.service;

import br.com.unipac.divan.divanapi.model.entities.patient.PatientProblem;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 *
 * @author Rogério Fontes
 */
@Repository
public interface PatientProblemService extends BaseService<PatientProblem, Long> {
}
