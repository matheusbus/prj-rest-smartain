package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceCauseComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCauseService {

    @Autowired
    private ServiceCauseRepository serviceCauseRepository;

    public List<ServiceCause> findAll() {
        return serviceCauseRepository.findAll();
    }

    public Optional<ServiceCause> findById(Integer id) {
        return serviceCauseRepository.findById(id);
    }

    @Transactional
    public void save(ServiceCause serviceCause) {
        serviceCauseRepository.save(serviceCause);
    }

    @Transactional
    public void saveAll(List<ServiceCause> serviceCauses) {
        serviceCauseRepository.saveAll(serviceCauses);
    }

}
