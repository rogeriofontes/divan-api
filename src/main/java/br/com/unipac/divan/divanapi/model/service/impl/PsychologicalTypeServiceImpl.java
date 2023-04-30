package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalType;
import br.com.unipac.divan.divanapi.model.repositories.PsychologicalTypeRepository;
import br.com.unipac.divan.divanapi.model.service.PsychologicalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PsychologicalTypeServiceImpl implements PsychologicalTypeService {

    @Autowired
    private PsychologicalTypeRepository psychologicalTypeRepository;

    @Override
    public PsychologicalType save(PsychologicalType psychologicalType) throws Exception {
        return psychologicalTypeRepository.save(psychologicalType);
    }

    @Override
    public PsychologicalType edit(Long id, PsychologicalType psychologicalType) {
        Optional<PsychologicalType> resultById = findById(id);

        if (resultById.isPresent()) {
            PsychologicalType psychologicalTypeToChange = resultById.get();
            psychologicalTypeToChange.update(id, psychologicalType);
            return psychologicalTypeRepository.save(psychologicalType);
        }

        return null;
    }

    @Override
    public List<PsychologicalType> listAll() {
        return psychologicalTypeRepository.findAll();
    }

    @Override
    public Page<PsychologicalType> findAllPageable(Pageable pageable) {
        return psychologicalTypeRepository.findAll(pageable);
    }

    @Override
    public Optional<PsychologicalType> findById(Long id) {
        return psychologicalTypeRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            psychologicalTypeRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
