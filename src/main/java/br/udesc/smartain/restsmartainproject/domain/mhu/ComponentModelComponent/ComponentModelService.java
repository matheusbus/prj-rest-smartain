package br.udesc.smartain.restsmartainproject.domain.mhu.ComponentModelComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModel;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModelRepository;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ComponentModelService {

    @Autowired
    private ComponentModelRepository componentModelRepository;

    public List<ComponentModel> findAll() {
        return componentModelRepository.findAll();
    }

    public List<ComponentModel> findAllByStatus(Short status) {
        return componentModelRepository.findAllByStatus(status);
    }

    public Optional<ComponentModel> findById(Integer id) {
        return componentModelRepository.findById(id);
    }

    @Transactional
    public ComponentModel save(ComponentModel componentModel) {
        return componentModelRepository.save(componentModel);
    }

    @Transactional
    public Object inactiveComponentModel(ComponentModel componentModelToInactive) {
        if(componentModelToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The ComponentModel " + componentModelToInactive.getId() + " is already inactive";
        }

        componentModelRepository.inactivateComponentModelById(componentModelToInactive.getId());
        return "Success! The ComponentModel " + componentModelToInactive.getId() + " has been inactived.";
    }

}
