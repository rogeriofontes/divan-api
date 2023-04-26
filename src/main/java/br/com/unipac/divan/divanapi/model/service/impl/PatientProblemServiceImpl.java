package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.association.AssociationSocialMedia;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientProblem;
import br.com.unipac.divan.divanapi.model.repositories.PatientProblemRepository;
import br.com.unipac.divan.divanapi.model.service.BaseService;
import br.com.unipac.divan.divanapi.model.service.PatientProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author Rogério Fontes
 */
@Service
public class PatientProblemServiceImpl implements PatientProblemService {

    @Autowired
    private PatientProblemRepository patientProblemRepository;

    @Override
    public PatientProblem save(PatientProblem patientProblem) throws Exception {
        return patientProblemRepository.save(patientProblem);
    }

    @Override
    public PatientProblem edit(Long id, PatientProblem patientProblem) {
        Optional<PatientProblem> resultById = findById(id);

        if (resultById.isPresent()) {
            PatientProblem patientProblemToChange = resultById.get();
            patientProblemToChange.update(id, patientProblem);
            return patientProblemRepository.save(patientProblem);
        }

        return null;
    }

    @Override
    public List<PatientProblem> listAll() {
        return patientProblemRepository.findAll();
    }

    @Override
    public Page<PatientProblem> findAllPageable(Pageable pageable) {
        return patientProblemRepository.findAll(pageable);
    }

    @Override
    public Optional<PatientProblem> findById(Long id) {
        return patientProblemRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            patientProblemRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
