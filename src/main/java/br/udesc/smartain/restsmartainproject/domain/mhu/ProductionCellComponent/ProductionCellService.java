package br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.ShiftComponent.Shift;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionCellService {

    @Autowired
    private ProductionCellRepository productionCellRepository;

    public List<ProductionCell> findAll() {
        return productionCellRepository.findAll();
    }

    public List<ProductionCell> findAllByStatus(Short status) {
        return productionCellRepository.findAllByStatus(status);
    }

    public Optional<ProductionCell> findById(Integer id) {
        return productionCellRepository.findById(id);
    }

    @Transactional
    public ProductionCell save(ProductionCell shift) {
        return productionCellRepository.save(shift);
    }

    @Transactional
    public Object inactiveProductionCell(ProductionCell productionCellToInactive) {
        if(productionCellToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The Production Cell " + productionCellToInactive.getId() + " is already inactive";
        }

        productionCellRepository.inacivateProductionCellById(productionCellToInactive.getId());
        return "Success! The Production Cell " + productionCellToInactive.getId() + " has been inactived.";
    }

}
