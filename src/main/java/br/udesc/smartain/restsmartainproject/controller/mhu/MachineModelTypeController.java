package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelTypeComponent.ModelTypeService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ModelTypeComponent.ModelType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/machineModelType")
public class MachineModelTypeController {
    
    @Autowired
    private ModelTypeService modelTypeService;

    @GetMapping
    public ResponseEntity<List<ModelType>> findAll() {
        List<ModelType> machineModelTypes = modelTypeService.findAll();

        if(machineModelTypes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(machineModelTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelType> findById(@PathVariable Integer id) {
        Optional<ModelType> machineModelType = modelTypeService.findById(id);

        if(machineModelType.isEmpty()) {
            throw new NotFoundException("MachineModelType id not found - " + id);
        }

        return ResponseEntity.ok(machineModelType.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<ModelType>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<ModelType> machineModelTypes = modelTypeService.findAllByStatus(status);
        if(machineModelTypes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(machineModelTypes);
    }

    @PostMapping
    public ResponseEntity<ModelType> createMachineModelType(@Valid @RequestBody ModelType machineModelType) {
        ModelType newMachineModelType = modelTypeService.save(machineModelType);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newMachineModelType.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelType> updateMachineModelType(@Valid @RequestBody ModelType newMachineModelType, @PathVariable Integer id) {
        Optional<ModelType> machineModelTypeToUpdate = modelTypeService.findById(id);

        if(machineModelTypeToUpdate.isEmpty()) {
            throw new NotFoundException("MachineModelType id not found - " + id);
        }

        machineModelTypeToUpdate = machineModelTypeToUpdate.map((machineModelTypeUpdated) -> {
            machineModelTypeUpdated.setDescription(newMachineModelType.getDescription());
            machineModelTypeUpdated.setDomainType(newMachineModelType.getDomainType());
            machineModelTypeUpdated.setStatus(newMachineModelType.getStatus());
            return machineModelTypeUpdated;
        });

        return ResponseEntity.ok(machineModelTypeToUpdate.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<ModelType> machineModelType = modelTypeService.findById(id);

        if(machineModelType.isEmpty()) {
            throw new NotFoundException("MachineModelType id not found - " + id);
        }
        return ResponseEntity.ok(modelTypeService.inactiveMachineModelType(machineModelType.get()));
    }
    
    
}
