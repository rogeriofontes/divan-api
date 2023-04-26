package br.com.unipac.divan.divanapi.model.service;

import br.com.unipac.divan.divanapi.model.entities.patient.PatientType;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 *
 * @author Rogério Fontes
 */
@Repository
public interface PatientTypeService extends BaseService<PatientType, Long> {
}
