package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.patient.PatientType;
import br.com.unipac.divan.divanapi.model.repositories.PatientTypeRepository;
import br.com.unipac.divan.divanapi.model.service.PatientTypeService;
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
public class PatientTypeServiceImpl implements PatientTypeService {

    @Autowired
    private PatientTypeRepository patientTypeRepository;

    @Override
    public PatientType save(PatientType patientType) throws Exception {
        return patientTypeRepository.save(patientType);
    }

    @Override
    public PatientType edit(Long id, PatientType patientType) {
        Optional<PatientType> resultById = findById(id);

        if (resultById.isPresent()) {
            PatientType patientTypeToChange = resultById.get();
            patientTypeToChange.update(id, patientType);
            return patientTypeRepository.save(patientType);
        }

        return null;
    }

    @Override
    public List<PatientType> listAll() {
        return patientTypeRepository.findAll();
    }

    @Override
    public Page<PatientType> findAllPageable(Pageable pageable) {
        return patientTypeRepository.findAll(pageable);
    }

    @Override
    public Optional<PatientType> findById(Long id) {
        return patientTypeRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            patientTypeRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
