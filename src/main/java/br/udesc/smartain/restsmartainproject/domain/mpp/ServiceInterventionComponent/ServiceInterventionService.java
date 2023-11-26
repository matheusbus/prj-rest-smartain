package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceInterventionComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceInterventionService {

    @Autowired
    private ServiceInterventionRepository serviceInterventionRepository;

    public List<ServiceIntervention> findAll() {
        return serviceInterventionRepository.findAll();
    }

    public Optional<ServiceIntervention> findById(Integer id) {
        return serviceInterventionRepository.findById(id);
    }

    @Transactional
    public void save(ServiceIntervention serviceIntervention) {
        serviceInterventionRepository.save(serviceIntervention);
    }

    @Transactional
    public void saveAll(List<ServiceIntervention> serviceInterventions) {
        serviceInterventionRepository.saveAll(serviceInterventions);
    }


}
