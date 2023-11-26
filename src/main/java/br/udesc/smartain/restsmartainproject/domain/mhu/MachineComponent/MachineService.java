package br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    public List<Machine> findAllByStatus(Short status) {
        return machineRepository.findAllByStatus(status);
    }

    public Optional<Machine> findById(Integer id) {
        return machineRepository.findById(id);
    }

    @Transactional
    public Machine save(Machine Machine) {
        return machineRepository.save(Machine);
    }

    @Transactional
    public Object inactiveMachine(Machine machineToInactive) {
        if(machineToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The Machine " + machineToInactive.getId() + " is already inactive";
        }

        machineRepository.inacivateMachineById(machineToInactive.getId());
        return "Success! The Machine " + machineToInactive.getId() + " has been inactived.";
    }

}
