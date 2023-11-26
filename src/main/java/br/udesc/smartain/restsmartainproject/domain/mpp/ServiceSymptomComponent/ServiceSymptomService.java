package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceSymptomService {

    @Autowired
    private ServiceSymptomRepository serviceSymptomRepository;

    public List<ServiceSymptom> findAll() {
        return serviceSymptomRepository.findAll();
    }

    public Optional<ServiceSymptom> findById(Integer id) {
        return serviceSymptomRepository.findById(id);
    }

    @Transactional
    public void save(ServiceSymptom serviceSymptom) {
        serviceSymptomRepository.save(serviceSymptom);
    }

    @Transactional
    public void saveAll(List<ServiceSymptom> serviceSymptoms) {
        serviceSymptomRepository.saveAll(serviceSymptoms);
    }

}
