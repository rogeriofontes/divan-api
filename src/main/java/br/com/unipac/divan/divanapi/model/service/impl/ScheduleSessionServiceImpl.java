package br.com.unipac.divan.divanapi.model.service.impl;
;
import br.com.unipac.divan.divanapi.model.entities.schedule.ScheduleSession;
import br.com.unipac.divan.divanapi.model.repositories.ScheduleSessionRepository;
import br.com.unipac.divan.divanapi.model.service.ScheduleSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleSessionServiceImpl implements ScheduleSessionService {

    @Autowired
    private ScheduleSessionRepository scheduleSessionRepository;

    @Override
    public ScheduleSession save(ScheduleSession scheduleSession) throws Exception {
        return scheduleSessionRepository.save(scheduleSession);
    }

    @Override
    public ScheduleSession edit(Long id, ScheduleSession scheduleSession) {
        Optional<ScheduleSession> resultById = findById(id);

        if (resultById.isPresent()) {
            ScheduleSession scheduleSessionToChange = resultById.get();
            scheduleSessionToChange.update(id, scheduleSession);
            return scheduleSessionRepository.save(scheduleSession);
        }

        return null;
    }

    @Override
    public List<ScheduleSession> listAll() {
        return scheduleSessionRepository.findAll();
    }

    @Override
    public Page<ScheduleSession> findAllPageable(Pageable pageable) {
        return scheduleSessionRepository.findAll(pageable);
    }

    @Override
    public Optional<ScheduleSession> findById(Long id) {
        return scheduleSessionRepository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        try {
            scheduleSessionRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
