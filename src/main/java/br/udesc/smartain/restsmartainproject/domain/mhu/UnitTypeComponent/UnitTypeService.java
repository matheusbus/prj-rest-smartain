package br.udesc.smartain.restsmartainproject.domain.mhu.UnitTypeComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitTypeService {

    @Autowired
    private UnitTypeRepository unitTypeRepository;

    public List<UnitType> findAll() {
        return unitTypeRepository.findAll();
    }

    public Optional<UnitType> findById(Integer id) {
        return unitTypeRepository.findById(id);
    }

    public void save(UnitType unitType) {
        unitTypeRepository.save(unitType);
    }

    public void saveAll(List<UnitType> unitTypes) {
        unitTypeRepository.saveAll(unitTypes);
    }

}
