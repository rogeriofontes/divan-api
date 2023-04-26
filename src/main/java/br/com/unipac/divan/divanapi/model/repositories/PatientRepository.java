package br.com.unipac.divan.divanapi.model.repositories;

import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 *
 * @author Rogério Fontes
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
