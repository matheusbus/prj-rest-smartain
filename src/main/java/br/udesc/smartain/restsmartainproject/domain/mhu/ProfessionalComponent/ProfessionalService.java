package br.udesc.smartain.restsmartainproject.domain.mhu.ProfessionalComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;

    public List<Professional> findAll() {
        return professionalRepository.findAll();
    }

    public List<Professional> findAllByStatus(Short status) {
        return professionalRepository.findAllByStatus(status);
    }

    public Optional<Professional> findById(Integer id) {
        return professionalRepository.findById(id);
    }

    @Transactional
    public Professional save(Professional professional) {
        return professionalRepository.save(professional);
    }

    @Transactional
    public Object inactiveProfesional(Professional professionalToInactive) {
        if(professionalToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The Professional " + professionalToInactive.getId() + " is already inactive";
        }

        professionalRepository.inacivateProfessionalById(professionalToInactive.getId());
        return "Success! The Professional " + professionalToInactive.getId() + " has been inactived.";
    }

}
