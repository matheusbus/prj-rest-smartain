package br.udesc.smartain.restsmartainproject.domain.mhu.SupplierComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.ShiftComponent.Shift;
import br.udesc.smartain.restsmartainproject.domain.mhu.ShiftComponent.ShiftRepository;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public List<Supplier> findAllByStatus(Short status) {
        return supplierRepository.findAllByStatus(status);
    }

    public Optional<Supplier> findById(Integer id) {
        return supplierRepository.findById(id);
    }

    @Transactional
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Transactional
    public Object inactiveSupplier(Supplier supplierToInactive) {
        if(supplierToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The supplier " + supplierToInactive.getId() + " is already inactive";
        }

        supplierRepository.inacivateSupplierById(supplierToInactive.getId());
        return "Success! The supplier " + supplierToInactive.getId() + " has been inactived.";
    }


}
