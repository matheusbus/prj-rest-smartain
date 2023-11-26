package br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MachineModelService {

    @Autowired
    private MachineModelRepository MachineModelRepository;

    public List<MachineModel> findAll() {
        return MachineModelRepository.findAll();
    }

    public List<MachineModel> findAllByStatus(Short status) {
        return MachineModelRepository.findAllByStatus(status);
    }

    public Optional<MachineModel> findById(Integer id) {
        return MachineModelRepository.findById(id);
    }

    @Transactional
    public MachineModel save(MachineModel MachineModel) {
        return MachineModelRepository.save(MachineModel);
    }

    @Transactional
    public Object inactiveMachineModel(MachineModel machineModelToInactive) {
        if(machineModelToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The MachineModel " + machineModelToInactive.getId() + " is already inactive";
        }

        MachineModelRepository.inacivateMachineModelById(machineModelToInactive.getId());
        return "Success! The MachineModel " + machineModelToInactive.getId() + " has been inactived.";
    }

}
