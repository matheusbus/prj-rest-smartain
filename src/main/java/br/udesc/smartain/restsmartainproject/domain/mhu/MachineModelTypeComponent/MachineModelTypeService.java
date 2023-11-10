package br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelTypeComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MachineModelTypeService {

    @Autowired
    private MachineModelTypeRepository machineModelTypeRepository;

    public List<MachineModelType> findAll() {
        return machineModelTypeRepository.findAll();
    }

    public List<MachineModelType> findAllByStatus(Short status) {
        return machineModelTypeRepository.findAllByStatus(status);
    }

    public Optional<MachineModelType> findById(Integer id) {
        return machineModelTypeRepository.findById(id);
    }

    @Transactional
    public MachineModelType save(MachineModelType MachineModelType) {
        return machineModelTypeRepository.save(MachineModelType);
    }

    @Transactional
    public Object inactiveMachineModelType(MachineModelType machineModelTypeToInactive) {
        if(machineModelTypeToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The MachineModelType " + machineModelTypeToInactive.getId() + " is already inactive";
        }

        machineModelTypeRepository.inacivateMachineModelTypeById(machineModelTypeToInactive.getId());
        return "Success! The MachineModelType " + machineModelTypeToInactive.getId() + " has been inactived.";
    }

}
