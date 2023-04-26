package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.patient.ProblemType;
import br.com.unipac.divan.divanapi.model.entities.psychological.Psychological;
import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalSpeciality;
import br.com.unipac.divan.divanapi.model.repositories.PsychologicalRepository;
import br.com.unipac.divan.divanapi.model.service.BaseService;
import br.com.unipac.divan.divanapi.model.service.PsychologicalService;
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
public class PsychologicalServiceImpl implements PsychologicalService {

    @Autowired
    private PsychologicalRepository psychologicalRepository;

    @Override
    public Psychological save(Psychological psychological) throws Exception {
        return psychologicalRepository.save(psychological);
    }

    @Override
    public Psychological edit(Long id, Psychological psychological) {
        Optional<Psychological> resultById = findById(id);

        if (resultById.isPresent()) {
            Psychological psychologicalToChange = resultById.get();
            psychologicalToChange.update(id, psychological);
            return psychologicalRepository.save(psychological);
        }

        return null;
    }

    @Override
    public List<Psychological> listAll() {
        return psychologicalRepository.findAll();
    }

    @Override
    public Page<Psychological> findAllPageable(Pageable pageable) {
        return psychologicalRepository.findAll(pageable);
    }

    @Override
    public Optional<Psychological> findById(Long id) {
        return psychologicalRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            psychologicalRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
