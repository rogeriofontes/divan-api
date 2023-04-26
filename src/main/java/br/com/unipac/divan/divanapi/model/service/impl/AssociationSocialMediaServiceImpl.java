package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.association.AssociationSocialMedia;
import br.com.unipac.divan.divanapi.model.repositories.AssociationSocialMediaRepository;
import br.com.unipac.divan.divanapi.model.service.AssociationSocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociationSocialMediaServiceImpl implements AssociationSocialMediaService {

    @Autowired
    private AssociationSocialMediaRepository associationSocialMediaRepository;

    @Override
    public AssociationSocialMedia save(AssociationSocialMedia associationSocialMedia) throws Exception {
        return associationSocialMediaRepository.save(associationSocialMedia);
    }

    @Override
    public AssociationSocialMedia edit(Long id, AssociationSocialMedia associationSocialMedia) {
        Optional<AssociationSocialMedia> resultById = findById(id);

        if (resultById.isPresent()) {
            AssociationSocialMedia associationToChange = resultById.get();
            associationToChange.update(id, associationSocialMedia);
            return associationSocialMediaRepository.save(associationSocialMedia);
        }

        return null;
    }

    @Override
    public List<AssociationSocialMedia> listAll() {
        return associationSocialMediaRepository.findAll();
    }

    @Override
    public Page<AssociationSocialMedia> findAllPageable(Pageable pageable) {
        return associationSocialMediaRepository.findAll(pageable);
    }

    @Override
    public Optional<AssociationSocialMedia> findById(Long id) {
        return associationSocialMediaRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            associationSocialMediaRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
