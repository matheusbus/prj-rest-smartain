package br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePriorityService {

    @Autowired
    private ServicePriorityRepository servicePriorityRepository;

    public List<ServicePriority> findAll() {
        return servicePriorityRepository.findAll();
    }

    public Optional<ServicePriority> findById(Integer id) {
        return servicePriorityRepository.findById(id);
    }

    @Transactional
    public void save(ServicePriority servicePriority) {
        servicePriorityRepository.save(servicePriority);
    }

    @Transactional
    public void saveAll(List<ServicePriority> servicePriorities) {
        servicePriorityRepository.saveAll(servicePriorities);
    }

}
