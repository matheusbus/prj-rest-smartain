package br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturingUnitService {

    @Autowired
    private ManufacturingUnitRepository manufacturingUnitRepository;

    public Optional<List<ManufacturingUnit>> findAll() {
        return manufacturingUnitRepository.findAllUnits();
    }

    public Optional<List<ManufacturingUnit>> findAllByStatus(Short status) {
        return manufacturingUnitRepository.findAllByStatus(status);
    }

    public Optional<ManufacturingUnit> findById(Integer id) {
        return manufacturingUnitRepository.findById(id);
    }

    @Transactional
    public ManufacturingUnit save(ManufacturingUnit manufacturingUnitToSave) {
        return manufacturingUnitRepository.save(manufacturingUnitToSave);
    }

    @Transactional
    public Object inactivateUnit(ManufacturingUnit manufacturingUnitToInactive) {
        if(manufacturingUnitToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The unit " + manufacturingUnitToInactive.getId() + " is already inactive.";
        }

        manufacturingUnitRepository.inacivateUnitById(manufacturingUnitToInactive.getId());
        return "Success! The unit " + manufacturingUnitToInactive.getId() + " has been inactivated.";
    }


}
