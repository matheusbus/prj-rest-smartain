package br.udesc.smartain.restsmartainproject.domain.mhu.MachineManualComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MachineManualService {

    @Autowired
    private MachineManualRepository machineManualRepository;

    public List<MachineManual> findAll() {
        return machineManualRepository.findAll();
    }

    public List<MachineManual> findAllByStatus(Short status) {
        return machineManualRepository.findAllByStatus(status);
    }

    public Optional<MachineManual> findById(Integer id) {
        return machineManualRepository.findById(id);
    }

    @Transactional
    public MachineManual save(MachineManual machineManual) {
        return machineManualRepository.save(machineManual);
    }

    @Transactional
    public Object inactiveMachineManual(MachineManual machineManualToInactive) {
        if(machineManualToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The Machine Manual " + machineManualToInactive.getId() + " is already inactive";
        }

        machineManualRepository.inacivateMachineManualById(machineManualToInactive.getId());
        return "Success! The Machine Manual " + machineManualToInactive.getId() + " has been inactived.";
    }


}
