package br.com.unipac.divan.divanapi.model.service;

import br.com.unipac.divan.divanapi.model.entities.association.project.Project;
import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 *
 * @author Rogério Fontes
 */
@Repository
public interface ProjectService extends BaseService<Project, Long> {
}
