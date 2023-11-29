package br.udesc.smartain.restsmartainproject.domain.mhu.ModelTypeComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ModelTypeService {

    @Autowired
    private ModelTypeRepository modelTypeRepository;

    public List<ModelType> findAll() {
        return modelTypeRepository.findAll();
    }

    public List<ModelType> findAllByStatus(Short status) {
        return modelTypeRepository.findAllByStatus(status);
    }

    public Optional<ModelType> findById(Integer id) {
        return modelTypeRepository.findById(id);
    }

    @Transactional
    public ModelType save(ModelType MachineModelType) {
        return modelTypeRepository.save(MachineModelType);
    }

    @Transactional
    public Object inactiveMachineModelType(ModelType machineModelTypeToInactive) {
        if(machineModelTypeToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The MachineModelType " + machineModelTypeToInactive.getId() + " is already inactive";
        }

        modelTypeRepository.inacivateMachineModelTypeById(machineModelTypeToInactive.getId());
        return "Success! The MachineModelType " + machineModelTypeToInactive.getId() + " has been inactived.";
    }

}
