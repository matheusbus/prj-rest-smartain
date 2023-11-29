package br.udesc.smartain.restsmartainproject.domain.mhu.ComponentComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.MachineRepository;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ComponentService {

    @Autowired
    private ComponentRepository componentRepository;

    public List<Component> findAll() {
        return componentRepository.findAll();
    }

    public List<Component> findAllByStatus(Short status) {
        return componentRepository.findAllByStatus(status);
    }

    public Optional<Component> findById(Integer id) {
        return componentRepository.findById(id);
    }

    @Transactional
    public Component save(Component component) {
        return componentRepository.save(component);
    }

    @Transactional
    public Object inactiveComponent(Component componentToInactive) {
        if(componentToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The Component " + componentToInactive.getId() + " is already inactive";
        }

        componentRepository.inactivateComponentById(componentToInactive.getId());
        return "Success! The Component " + componentToInactive.getId() + " has been inactived.";
    }


}
