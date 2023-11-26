package br.udesc.smartain.restsmartainproject.controller.mpp;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceType;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mpp/maintenanceType")
public class MaintenanceTypeController {

    @Autowired
    private MaintenanceTypeService maintenanceTypeService;

    @GetMapping
    public ResponseEntity<List<MaintenanceType>> findAll() {
        List<MaintenanceType> maintenanceTypes = maintenanceTypeService.findAll();

        if(maintenanceTypes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(maintenanceTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceType> findById(@PathVariable Integer id) {
        Optional<MaintenanceType> maintenanceType = maintenanceTypeService.findById(id);

        if(maintenanceType.isEmpty()) {
            throw new NotFoundException("Maintenance Type id not found - " + id);
        }

        return ResponseEntity.ok(maintenanceType.get());
    }

}
