package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelTypeComponent.MachineModelTypeService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelTypeComponent.MachineModelType;
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
    private MachineModelTypeService machineModelTypeService;

    @GetMapping
    public ResponseEntity<List<MachineModelType>> findAll() {
        List<MachineModelType> machineModelTypes = machineModelTypeService.findAll();

        if(machineModelTypes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(machineModelTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MachineModelType> findById(@PathVariable Integer id) {
        Optional<MachineModelType> machineModelType = machineModelTypeService.findById(id);

        if(machineModelType.isEmpty()) {
            throw new NotFoundException("MachineModelType id not found - " + id);
        }

        return ResponseEntity.ok(machineModelType.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<MachineModelType>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 || status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<MachineModelType> machineModelTypes = machineModelTypeService.findAllByStatus(status);
        if(machineModelTypes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(machineModelTypes);
    }

    @PostMapping
    public ResponseEntity<MachineModelType> createMachineModelType(@Valid @RequestBody MachineModelType machineModelType) {
        MachineModelType newMachineModelType = machineModelTypeService.save(machineModelType);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newMachineModelType.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MachineModelType> updateMachineModelType(@Valid @RequestBody MachineModelType newMachineModelType, @PathVariable Integer id) {
        Optional<MachineModelType> machineModelTypeToUpdate = machineModelTypeService.findById(id);

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
        Optional<MachineModelType> machineModelType = machineModelTypeService.findById(id);

        if(machineModelType.isEmpty()) {
            throw new NotFoundException("MachineModelType id not found - " + id);
        }
        return ResponseEntity.ok(machineModelTypeService.inactiveMachineModelType(machineModelType.get()));
    }
    
    
}
