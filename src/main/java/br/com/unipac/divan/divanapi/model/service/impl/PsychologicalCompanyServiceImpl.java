package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalCompany;
import br.com.unipac.divan.divanapi.model.repositories.PsychologicalCompanyRepository;
import br.com.unipac.divan.divanapi.model.service.PsychologicalCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PsychologicalCompanyServiceImpl implements PsychologicalCompanyService {

    @Autowired
    private PsychologicalCompanyRepository psychologicalCompanyRepository;

    @Override
    public PsychologicalCompany save(PsychologicalCompany psychologicalCompany) throws Exception {
        return psychologicalCompanyRepository.save(psychologicalCompany);
    }

    @Override
    public PsychologicalCompany edit(Long id, PsychologicalCompany psychologicalCompany) {
        Optional<PsychologicalCompany> resultById = findById(id);

        if (resultById.isPresent()) {
            PsychologicalCompany psychologicalCompanyToChange = resultById.get();
            psychologicalCompanyToChange.update(id, psychologicalCompany);
            return psychologicalCompanyRepository.save(psychologicalCompany);
        }

        return null;
    }

    @Override
    public List<PsychologicalCompany> listAll() {
        return psychologicalCompanyRepository.findAll();
    }

    @Override
    public Page<PsychologicalCompany> findAllPageable(Pageable pageable) {
        return psychologicalCompanyRepository.findAll(pageable);
    }

    @Override
    public Optional<PsychologicalCompany> findById(Long id) {
        return psychologicalCompanyRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            psychologicalCompanyRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
