package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModelService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/machineModel")
public class MachineModelController {
    
    @Autowired
    private MachineModelService machineModelService;

    @GetMapping
    public ResponseEntity<List<MachineModel>> findAll() {
        List<MachineModel> MachineModels = machineModelService.findAll();

        if(MachineModels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(MachineModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MachineModel> findById(@PathVariable Integer id) {
        Optional<MachineModel> MachineModel = machineModelService.findById(id);

        if(MachineModel.isEmpty()) {
            throw new NotFoundException("MachineModel id not found - " + id);
        }

        return ResponseEntity.ok(MachineModel.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<MachineModel>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<MachineModel> MachineModels = machineModelService.findAllByStatus(status);
        if(MachineModels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(MachineModels);
    }

    @PostMapping
    public ResponseEntity<MachineModel> createMachineModel(@Valid @RequestBody MachineModel MachineModel) {
        MachineModel newMachineModel = machineModelService.save(MachineModel);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newMachineModel.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MachineModel> updateMachineModel(@Valid @RequestBody MachineModel newMachineModel, @PathVariable Integer id) {
        Optional<MachineModel> MachineModelToUpdate = machineModelService.findById(id);

        if(MachineModelToUpdate.isEmpty()) {
            throw new NotFoundException("MachineModel id not found - " + id);
        }

        MachineModelToUpdate = MachineModelToUpdate.map((MachineModelUpdated) -> {
            MachineModelUpdated.setModel(newMachineModel.getModel());
            MachineModelUpdated.setManufacturer(newMachineModel.getManufacturer());
            MachineModelUpdated.setDimensions(newMachineModel.getDimensions());
            MachineModelUpdated.setMachineModelType(newMachineModel.getMachineModelType());
            MachineModelUpdated.setStatus(newMachineModel.getStatus());
            return MachineModelUpdated;
        });

        return ResponseEntity.ok(MachineModelToUpdate.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<MachineModel> MachineModel = machineModelService.findById(id);

        if(MachineModel.isEmpty()) {
            throw new NotFoundException("MachineModel id not found - " + id);
        }
        return ResponseEntity.ok(machineModelService.inactiveMachineModel(MachineModel.get()));
    }
    
}
