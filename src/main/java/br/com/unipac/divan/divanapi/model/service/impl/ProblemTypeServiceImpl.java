package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.patient.ProblemType;
import br.com.unipac.divan.divanapi.model.repositories.ProblemTypeRepository;
import br.com.unipac.divan.divanapi.model.service.ProblemTypeService;
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
public class ProblemTypeServiceImpl implements ProblemTypeService {

    @Autowired
    private ProblemTypeRepository problemTypeRepository;

    @Override
    public ProblemType save(ProblemType problemType) throws Exception {
        return problemTypeRepository.save(problemType);
    }

    @Override
    public ProblemType edit(Long id, ProblemType problemType) {
        Optional<ProblemType> resultById = findById(id);

        if (resultById.isPresent()) {
            ProblemType problemTypeToChange = resultById.get();
            problemTypeToChange.update(id, problemType);
            return problemTypeRepository.save(problemType);
        }

        return null;
    }

    @Override
    public List<ProblemType> listAll() {
        return problemTypeRepository.findAll();
    }

    @Override
    public Page<ProblemType> findAllPageable(Pageable pageable) {
        return problemTypeRepository.findAll(pageable);
    }

    @Override
    public Optional<ProblemType> findById(Long id) {
        return problemTypeRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            problemTypeRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
