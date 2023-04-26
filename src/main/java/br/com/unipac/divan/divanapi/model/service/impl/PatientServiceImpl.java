package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientProblem;
import br.com.unipac.divan.divanapi.model.repositories.PatientRepository;
import br.com.unipac.divan.divanapi.model.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author Rogério Fontes
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) throws Exception {
        return patientRepository.save(patient);
    }

    @Override
    public Patient edit(Long id, Patient patient) {
        Optional<Patient> resultById = findById(id);

        if (resultById.isPresent()) {
            Patient patientToChange = resultById.get();
            patientToChange.update(id, patient);
            return patientRepository.save(patient);
        }

        return null;
    }

    @Override
    public List<Patient> listAll() {
        return patientRepository.findAll();
    }

    @Override
    public Page<Patient> findAllPageable(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            patientRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
