package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.association.project.Project;
import br.com.unipac.divan.divanapi.model.repositories.ProjectRepository;
import br.com.unipac.divan.divanapi.model.service.ProjectService;
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
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project save(Project project) throws Exception {
        return projectRepository.save(project);
    }

    @Override
    public Project edit(Long id, Project project) {
        Optional<Project> resultById = findById(id);

        if (resultById.isPresent()) {
            Project projectToChange = resultById.get();
            projectToChange.update(id, project);
            return projectRepository.save(project);
        }

        return null;
    }

    @Override
    public List<Project> listAll() {
        return projectRepository.findAll();
    }

    @Override
    public Page<Project> findAllPageable(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            projectRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
