package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalSpeciality;
import br.com.unipac.divan.divanapi.model.repositories.PsychologicalSpecialityRepository;
import br.com.unipac.divan.divanapi.model.service.PsychologicalSpecialityService;
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
public class PsychologicalSpecialityServiceImpl implements PsychologicalSpecialityService {

    @Autowired
    private PsychologicalSpecialityRepository psychologicalSpecialityRepository;

    @Override
    public PsychologicalSpeciality save(PsychologicalSpeciality psychologicalSpeciality) throws Exception {
        return psychologicalSpecialityRepository.save(psychologicalSpeciality);
    }

    @Override
    public PsychologicalSpeciality edit(Long id, PsychologicalSpeciality psychologicalSpeciality) {
        Optional<PsychologicalSpeciality> resultById = findById(id);

        if (resultById.isPresent()) {
            PsychologicalSpeciality psychologicalSpecialityToChange = resultById.get();
            psychologicalSpecialityToChange.update(id, psychologicalSpeciality);
            return psychologicalSpecialityRepository.save(psychologicalSpeciality);
        }

        return null;
    }

    @Override
    public List<PsychologicalSpeciality> listAll() {
        return psychologicalSpecialityRepository.findAll();
    }

    @Override
    public Page<PsychologicalSpeciality> findAllPageable(Pageable pageable) {
        return psychologicalSpecialityRepository.findAll(pageable);
    }

    @Override
    public Optional<PsychologicalSpeciality> findById(Long id) {
        return psychologicalSpecialityRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            psychologicalSpecialityRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
