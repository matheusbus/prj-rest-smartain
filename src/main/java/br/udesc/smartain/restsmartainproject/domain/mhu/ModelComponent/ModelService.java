package br.udesc.smartain.restsmartainproject.domain.mhu.ModelComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    @Autowired
    private ModelRepository ModelRepository;

    public List<Model> findAll() {
        return ModelRepository.findAll();
    }

    public List<Model> findAllByStatus(Short status) {
        return ModelRepository.findAllByStatus(status);
    }

    public Optional<Model> findById(Integer id) {
        return ModelRepository.findById(id);
    }

    @Transactional
    public Model save(Model Model) {
        return ModelRepository.save(Model);
    }

    @Transactional
    public Object inactiveModel(Model modelToInactive) {
        if(modelToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The Model " + modelToInactive.getId() + " is already inactive";
        }

        ModelRepository.inacivateModelById(modelToInactive.getId());
        return "Success! The Model " + modelToInactive.getId() + " has been inactived.";
    }

}
