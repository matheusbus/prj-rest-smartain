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
    public Object inactiveMachineModel(MachineModel MachineModelToInactive) {
        if(MachineModelToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The MachineModel " + MachineModelToInactive.getId() + " is already inactive";
        }

        MachineModelRepository.inacivateMachineModelById(MachineModelToInactive.getId());
        return "Success! The MachineModel " + MachineModelToInactive.getId() + " has been inactived.";
    }

}
