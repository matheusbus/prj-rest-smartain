package br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentModelComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentModelService {

    @Autowired
    private EquipmentModelRepository equipmentModelRepository;

    public List<EquipmentModel> findAll() {
        return equipmentModelRepository.findAll();
    }

    public List<EquipmentModel> findAllByStatus(Short status) {
        return equipmentModelRepository.findAllByStatus(status);
    }

    public Optional<EquipmentModel> findById(Integer id) {
        return equipmentModelRepository.findById(id);
    }

    @Transactional
    public EquipmentModel save(EquipmentModel equipmentModel) {
        return equipmentModelRepository.save(equipmentModel);
    }

    @Transactional
    public Object inactiveEquipmentModel(EquipmentModel equipmentModelToInactive) {
        if(equipmentModelToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The EquipmentModel " + equipmentModelToInactive.getId() + " is already inactive";
        }

        equipmentModelRepository.inactivateEquipmentModelById(equipmentModelToInactive.getId());
        return "Success! The EquipmentModel " + equipmentModelToInactive.getId() + " has been inactived.";
    }

}
