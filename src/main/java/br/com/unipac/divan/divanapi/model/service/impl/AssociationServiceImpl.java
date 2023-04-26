package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.association.Association;
import br.com.unipac.divan.divanapi.model.repositories.AssociationRepository;
import br.com.unipac.divan.divanapi.model.service.AssociationService;
import br.com.unipac.divan.divanapi.model.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociationServiceImpl implements AssociationService {

    @Autowired
    private AssociationRepository associationRepository;

    @Override
    public Association save(Association association) throws Exception {
        return associationRepository.save(association);
    }

    @Override
    public Association edit(Long id, Association association) {
        Optional<Association> resultById = findById(id);

        if (resultById.isPresent()) {
            Association associationToChange = resultById.get();
            associationToChange.update(id, association);
            return associationRepository.save(association);
        }

        return null;
    }

    @Override
    public List<Association> listAll() {
        return associationRepository.findAll();
    }

    @Override
    public Page<Association> findAllPageable(Pageable pageable) {
        return associationRepository.findAll(pageable);
    }

    @Override
    public Optional<Association> findById(Long id) {
        return associationRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            associationRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
