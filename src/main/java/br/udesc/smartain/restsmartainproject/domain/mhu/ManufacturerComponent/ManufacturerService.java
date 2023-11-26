package br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.ShiftComponent.Shift;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    public List<Manufacturer> findAllByStatus(Short status) {
        return manufacturerRepository.findAllByStatus(status);
    }

    public Optional<Manufacturer> findById(Integer id) {
        return manufacturerRepository.findById(id);
    }

    @Transactional
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Transactional
    public Object inactiveManufacturer(Manufacturer manufacturerToInactive) {
        if(manufacturerToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The manufacturer " + manufacturerToInactive.getId() + " is already inactive";
        }

        manufacturerRepository.inacivateManufacturerById(manufacturerToInactive.getId());
        return "Success! The manufacturer " + manufacturerToInactive.getId() + " has been inactived.";
    }

}
