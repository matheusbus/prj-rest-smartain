package br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    public List<Equipment> findAllByStatus(Short status) {
        return equipmentRepository.findAllByStatus(status);
    }

    public Optional<Equipment> findById(Integer id) {
        return equipmentRepository.findById(id);
    }

    @Transactional
    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Transactional
    public Object inactiveEquipment(Equipment equipmentToInactive) {
        if(equipmentToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The Equipment " + equipmentToInactive.getId() + " is already inactive";
        }

        equipmentRepository.inactivateEquipmentById(equipmentToInactive.getId());
        return "Success! The Equipment " + equipmentToInactive.getId() + " has been inactived.";
    }

}
