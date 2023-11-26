package br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent;

import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptom;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceTypeService {

    @Autowired
    private MaintenanceTypeRepository maintenanceTypeRepository;

    public List<MaintenanceType> findAll() {
        return maintenanceTypeRepository.findAll();
    }

    public Optional<MaintenanceType> findById(Integer id) {
        return maintenanceTypeRepository.findById(id);
    }

    @Transactional
    public void save(MaintenanceType maintenanceType) {
        maintenanceTypeRepository.save(maintenanceType);
    }

    @Transactional
    public void saveAll(List<MaintenanceType> maintenanceTypes) {
        maintenanceTypeRepository.saveAll(maintenanceTypes);
    }

}
