package br.udesc.smartain.restsmartainproject.domain.mpp.OrderGenerationTypeComponent;

import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderGenerationTypeService {

    @Autowired
    private OrderGenerationTypeRepository orderGenerationTypeRepository;

    public List<OrderGenerationType> findAll() {
        return orderGenerationTypeRepository.findAll();
    }

    public Optional<OrderGenerationType> findById(Integer id) {
        return orderGenerationTypeRepository.findById(id);
    }

    @Transactional
    public void save(OrderGenerationType orderGenerationType) {
        orderGenerationTypeRepository.save(orderGenerationType);
    }

    @Transactional
    public void saveAll(List<OrderGenerationType> orderGenerationTypes) {
        orderGenerationTypeRepository.saveAll(orderGenerationTypes);
    }


}
